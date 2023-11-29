package com.lodi.xo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.constant.StatusConstant;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.service.impl.BaseServiceImpl;
import com.lodi.common.model.convert.article.ArticleConvert;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.entity.Category;
import com.lodi.common.model.entity.Tags;
import com.lodi.common.model.request.article.ArticleAddRequest;
import com.lodi.common.model.request.article.ArticlePageRequest;
import com.lodi.common.model.request.article.ArticleUpdateRequest;
import com.lodi.common.model.request.article.AuditArticleRequest;
import com.lodi.common.model.vo.ArticleVO;
import com.lodi.xo.mapper.ArticleMapper;
import com.lodi.xo.service.ArticleService;
import com.lodi.xo.service.CategoryService;
import com.lodi.xo.service.TagsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.lodi.common.core.constant.ArticleConstant.CONTENT;
import static com.lodi.common.core.constant.ArticleConstant.PAGE_SIZE;

/**
 * 文章 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private TagsService tagsService;
    @Resource
    private CategoryService categoryService;

    @Override
    public Boolean insertArticle(ArticleAddRequest addRequest) {
        // 获取当前用户信息

        // 判断标签id、类别id 是否存在

        Article article = ArticleConvert.INSTANCE.toEntity(addRequest);

        // todo 临时设置
        article.setUserId(1L);

        return save(article);
    }

    @Override
    public Boolean updateArticle(ArticleUpdateRequest updateRequest) {
        // 判断是否当前用户或管理员

        // 判断标签id、类别id 是否存在

        Article article = ArticleConvert.INSTANCE.toEntity(updateRequest);

        return updateById(article);
    }

    @Override
    public Boolean deleteArticle(Long id) {
        // 判断是否当前用户或管理员

        return removeById(id);
    }

    private LambdaQueryWrapper<Article> buildQueryWrapper(ArticlePageRequest pageRequest) {
        return new LambdaQueryWrapper<Article>()
                .like(StringUtils.isNotBlank(pageRequest.getTitle()),
                        Article::getTitle, pageRequest.getTitle())
                .like(StringUtils.isNotBlank(pageRequest.getSummary()),
                        Article::getSummary, pageRequest.getSummary())
                .like(StringUtils.isNotBlank(pageRequest.getContent()),
                        Article::getContent, pageRequest.getContent())
                .eq(Objects.nonNull(pageRequest.getUserId()),
                        Article::getUserId, pageRequest.getUserId())
                .eq(Objects.nonNull(pageRequest.getCategoryId()),
                        Article::getCategoryId, pageRequest.getCategoryId())
                .eq(Objects.nonNull(pageRequest.getIsPublish()),
                        Article::getIsPublish, pageRequest.getIsPublish())
                .eq(Objects.nonNull(pageRequest.getOpenComment()),
                        Article::getOpenComment, pageRequest.getOpenComment())
                .eq(Objects.nonNull(pageRequest.getVipArticle()),
                        Article::getVipArticle, pageRequest.getVipArticle())
                .eq(Objects.nonNull(pageRequest.getAuditStatus()),
                        Article::getAuditStatus, pageRequest.getAuditStatus());
    }

    @Override
    public Boolean auditArticle(AuditArticleRequest auditRequest) {
        Integer newStatus = auditRequest.getAuditStatus();
        // 检查文章是否存在
        Article article = baseMapper.selectById(auditRequest.getId());
        if (Objects.isNull(article)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "未找到文章");
        }
        List auditStatus = new ArrayList<>(Arrays.asList(0, 1, 2));
        // 判断状态参数是否合法
        if (!auditStatus.contains(newStatus)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        // 状态与当前状态相同，则不执行操作
        if (newStatus == article.getAuditStatus()) {
            return true;
        }
        // todo 直接使用article进行更新（updateTime 未设置自动填充）
        Article newArticle = new Article();
        newArticle.setId(auditRequest.getId());
        newArticle.setAuditStatus(newStatus);
        return updateById(newArticle);
    }

    @Override
    public Page<ArticleVO> getArticlePage(ArticlePageRequest articlePageRequest) {
        Page<Article> page = new Page<>(articlePageRequest.getCurrent(), articlePageRequest.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = buildQueryWrapper(articlePageRequest);
        Page<Article> articlePage = baseMapper.selectPage(page, queryWrapper);

        return convertToArticleVOPage(articlePage);
    }

    @Override
    public Page<ArticleVO> getArticleByOrderDesc(Long currentPage, String column) {
        LambdaQueryWrapper<Article> queryWrapper = buildCommonQueryWrapper();

        // 排序字段，排序相同时则再根据user_id和文章id排序
        queryWrapper.last(String.format("order by %s desc, user_id, id ", column));
        Page<Article> wherePage = new Page<>(currentPage, PAGE_SIZE);

        // 分页查询
        Page<Article> articlePage = baseMapper.selectPage(wherePage, queryWrapper);
        return convertToArticleVOPage(articlePage);
    }

    @Override
    public Page<ArticleVO> getArticleBySearch(Long currentPage, String keyword) {
        LambdaQueryWrapper<Article> queryWrapper = buildCommonQueryWrapper();

        // 不获取内容字段值
        queryWrapper.like(Article::getTitle, keyword)
                .or().like(Article::getSummary, keyword)
                .or().like(Article::getContent, keyword);

        // 按文章创建时间、用户id、文章id排序（倒序）
        queryWrapper.orderByDesc(Article::getCreateTime, Article::getUserId, Article::getId);
        Page<Article> wherePage = new Page<>(currentPage, PAGE_SIZE);

        // 分页查询
        Page<Article> articlePage = baseMapper.selectPage(wherePage, queryWrapper);
        return convertToArticleVOPage(articlePage);
    }

    private Page<ArticleVO> convertToArticleVOPage(Page<Article> articlePage) {
        Page<ArticleVO> articleVOPage = ArticleConvert.INSTANCE.toVO(articlePage);
        List<ArticleVO> list = articleVOPage.getRecords();

        // 设置分类和标签
        setCategoryByArticleVOList(list);
        setTagByArticleVOList(list);

        return articleVOPage;
    }

    /**
     * 构建公共查询条件
     * 1、排除内容字段
     * 2、文章发布状态为公开
     *
     * @return
     */
    private LambdaQueryWrapper<Article> buildCommonQueryWrapper() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 不获取内容字段值
        queryWrapper.select(Article.class, i -> !i.getProperty().equals(CONTENT));
        queryWrapper.eq(Article::getIsPublish, StatusConstant.ON);
        return queryWrapper;
    }

    @Override
    public void setTagByArticleVO(ArticleVO articleVO) {
        // 获取标签id
        String tagIds = articleVO.getTagId();
        if (StringUtils.isBlank(tagIds)) {
            return;
        }
        String[] tagIdArray = tagIds.split(",");
        List<String> tagsIdList = Arrays.stream(tagIdArray).collect(Collectors.toList());
        // 查询标签信息
        List<Tags> tagsList = tagsService.listByIds(tagsIdList);

        // 得到标签名列表
        List<String> tagsNameList = tagsList.stream().map(Tags::getName).collect(Collectors.toList());
        articleVO.setTagsList(tagsNameList);
    }

    @Override
    public void setCategoryByArticleVO(ArticleVO articleVO) {
        // 查询分类信息
        Category category = categoryService.getById(articleVO.getCategoryId());
        if (Objects.isNull(category)) {
            return;
        }
        articleVO.setCategory(category.getName());
    }

    @Override
    public void setTagByArticleVOList(List<ArticleVO> articleVOList) {
        for (ArticleVO articleVO : articleVOList) {
            setTagByArticleVO(articleVO);
        }
    }

    @Override
    public void setCategoryByArticleVOList(List<ArticleVO> articleVOList) {
        for (ArticleVO articleVO : articleVOList) {
            setCategoryByArticleVO(articleVO);
        }
    }

}

