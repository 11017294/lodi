package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.constant.StatusConstant;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.holder.SecurityContextHolder;
import com.lodi.common.core.utils.SecurityUtils;
import com.lodi.common.model.convert.article.ArticleConvert;
import com.lodi.common.model.entity.*;
import com.lodi.common.model.request.article.ArticleAddRequest;
import com.lodi.common.model.request.article.ArticlePageRequest;
import com.lodi.common.model.request.article.ArticleUpdateRequest;
import com.lodi.common.model.request.article.AuditArticleRequest;
import com.lodi.common.model.vo.ArticleVO;
import com.lodi.common.mybatis.page.PageRequest;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import com.lodi.xo.mapper.ArticleMapper;
import com.lodi.xo.mapper.CommentMapper;
import com.lodi.xo.service.ArticleService;
import com.lodi.xo.service.CategoryService;
import com.lodi.xo.service.TagsService;
import com.lodi.xo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
@Slf4j
@Service
@AllArgsConstructor
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements ArticleService {

    private TagsService tagsService;
    private CategoryService categoryService;
    private UserService userService;
    private CommentMapper commentsMapper;

    @Override
    public Boolean insertArticle(ArticleAddRequest addRequest) {
        Long[] tags = addRequest.getTags();
        Long categoryId = addRequest.getCategoryId();
        // 判断标签id、类别id 是否存在
        tagsService.validateTagsId(tags);
        categoryService.validateCategoryId(categoryId);

        Article article = ArticleConvert.INSTANCE.toEntity(addRequest);
        // 设置作者id
        article.setUserId(SecurityContextHolder.getUserId());

        return save(article);
    }

    @Override
    public Boolean updateArticle(ArticleUpdateRequest updateRequest) {
        Long[] tags = updateRequest.getTags();
        Long categoryId = updateRequest.getCategoryId();
        // 判断标签id、类别id 是否存在
        tagsService.validateTagsId(tags);
        categoryService.validateCategoryId(categoryId);

        Article article = ArticleConvert.INSTANCE.toEntity(updateRequest);
        // 判断是否当前用户或管理员
        isCurrentUserOrAdmin(article.getId());
        return updateById(article);
    }

    @Override
    public Boolean deleteArticle(Long id) {
        // 判断是否当前用户或管理员
        isCurrentUserOrAdmin(id);
        return removeById(id);
    }

    /**
     * 判断是否当前用户或管理员
     *
     * @param articleId 文章id
     */
    private void isCurrentUserOrAdmin(Long articleId) {
        if (articleId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Article::getUserId)
                .eq(Article::getId, articleId);

        Article article = baseMapper.selectOne(queryWrapper);
        if (article == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        SecurityUtils.isCurrentUserOrAdmin(article.getUserId());
    }

    private LambdaQueryWrapper<Article> buildQueryWrapper(ArticlePageRequest pageRequest) {
        return new LambdaQueryWrapper<Article>()
                .like(StringUtils.isNotBlank(pageRequest.getTitle()), Article::getTitle, pageRequest.getTitle())
                .like(StringUtils.isNotBlank(pageRequest.getSummary()), Article::getSummary, pageRequest.getSummary())
                .like(StringUtils.isNotBlank(pageRequest.getContent()), Article::getContent, pageRequest.getContent())
                .eq(Objects.nonNull(pageRequest.getUserId()), Article::getUserId, pageRequest.getUserId())
                .eq(Objects.nonNull(pageRequest.getCategoryId()), Article::getCategoryId, pageRequest.getCategoryId())
                .eq(Objects.nonNull(pageRequest.getIsPublish()), Article::getIsPublish, pageRequest.getIsPublish())
                .eq(Objects.nonNull(pageRequest.getOpenComment()), Article::getOpenComment, pageRequest.getOpenComment())
                .eq(Objects.nonNull(pageRequest.getVipArticle()), Article::getVipArticle, pageRequest.getVipArticle())
                .eq(Objects.nonNull(pageRequest.getAuditStatus()), Article::getAuditStatus, pageRequest.getAuditStatus());
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
        Page<Article> page = new Page<>(articlePageRequest.getCurrentPage(), articlePageRequest.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = buildQueryWrapper(articlePageRequest);
        Page<Article> articlePage = baseMapper.selectPage(page, queryWrapper);

        return convertToArticleVOPage(articlePage);
    }

    @Override
    public Page<ArticleVO> getArticleByOrderDesc(PageRequest request) {
        LambdaQueryWrapper<Article> queryWrapper = buildCommonQueryWrapper();

        // 排序字段，排序相同时则再根据user_id和文章id排序
        queryWrapper.last(String.format("order by %s desc, user_id, id ", request.getSortField()));
        Page<Article> wherePage = new Page<>(request.getCurrentPage(), request.getPageSize());

        // 分页查询
        Page<Article> articlePage = baseMapper.selectPage(wherePage, queryWrapper);
        return convertToArticleVOPage(articlePage);
    }

    @Override
    public Page<ArticleVO> getArticleBySearchDesc(PageRequest request) {
        String keyword = request.getKeyword();
        Boolean haveKeyword = StringUtils.isBlank(keyword);

        LambdaQueryWrapper<Article> queryWrapper = buildCommonQueryWrapper();
        queryWrapper.like(haveKeyword, Article::getTitle, keyword)
                .or().like(haveKeyword, Article::getSummary, keyword)
                .or().like(haveKeyword, Article::getContent, keyword);

        // 按文章创建时间、用户id、文章id排序（倒序）
        queryWrapper.orderByDesc(Article::getId);
        Page<Article> wherePage = new Page<>(request.getCurrentPage(), request.getPageSize());

        // 分页查询
        Page<Article> articlePage = baseMapper.selectPage(wherePage, queryWrapper);
        return convertToArticleVOPage(articlePage);
    }

    @Override
    public Page<ArticleVO> getArticleByTagId(Long currentPage, Long tagId) {
        // 构建查询条件
        LambdaQueryWrapper<Article> queryWrapper = buildCommonQueryWrapper();
        queryWrapper.like(Article::getTagsId, tagId);
        queryWrapper.orderByDesc(Article::getId);

        // 执行分页查询
        Page<Article> wherePage = new Page<>(currentPage, PAGE_SIZE);
        Page<Article> articlePage = baseMapper.selectPage(wherePage, queryWrapper);

        // 修改标签点击次数
        tagsService.incrementClickCount(tagId);

        // 将查询结果转换为VO分页
        return convertToArticleVOPage(articlePage);
    }

    @Override
    public Page<ArticleVO> getArticleByCategoryId(Long currentPage, Long categoryId) {
        // 构建查询条件
        LambdaQueryWrapper<Article> queryWrapper = buildCommonQueryWrapper();
        queryWrapper.eq(Article::getCategoryId, categoryId);
        queryWrapper.orderByDesc(Article::getId);

        // 执行分页查询
        Page<Article> wherePage = new Page<>(currentPage, PAGE_SIZE);
        Page<Article> articlePage = baseMapper.selectPage(wherePage, queryWrapper);

        // 修改分类点击次数
        categoryService.incrementClickCount(categoryId);

        // 将查询结果转换为VO分页
        return convertToArticleVOPage(articlePage);
    }

    private Page<ArticleVO> convertToArticleVOPage(Page<Article> articlePage) {
        Page<ArticleVO> articleVOPage = ArticleConvert.INSTANCE.toVO(articlePage);
        List<ArticleVO> list = articleVOPage.getRecords();

        // 设置分类、标签和用户信息
        setCategoryByArticleVOList(list);
        setTagByArticleVOList(list);
        setUserInfoByArticleVOList(list);
        setCommentCountByArticleVOList(list);

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
        queryWrapper.eq(Article::getAuditStatus, StatusConstant.ON);
        return queryWrapper;
    }

    @Override
    public void setTagByArticleVO(ArticleVO articleVO) {
        // 获取标签id
        String tagsId = articleVO.getTagsId();
        if (StringUtils.isBlank(tagsId)) {
            return;
        }
        String[] tagsIdArray = tagsId.split(",");
        List<String> tagsIdList = Arrays.stream(tagsIdArray).collect(Collectors.toList());
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
        articleVO.setCategoryName(category.getName());
    }

    @Override
    public void setUserInfoByArticleVO(ArticleVO articleVO) {
        User user = userService.getById(articleVO.getUserId());
        if (Objects.isNull(user)) {
            return;
        }
        articleVO.setNickname(user.getNickname());
        articleVO.setUsername(user.getUsername());
        articleVO.setUserAvatar(user.getUserAvatar());
    }

    @Override
    public void setCommentCountByArticleVO(ArticleVO articleVO) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleVO.getId());
        Long commentCount = commentsMapper.selectCount(queryWrapper);
        articleVO.setCommentCount(commentCount);
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

    @Override
    public void setUserInfoByArticleVOList(List<ArticleVO> articleVOList) {
        for (ArticleVO articleVO : articleVOList) {
            setUserInfoByArticleVO(articleVO);
        }
    }

    @Override
    public void setCommentCountByArticleVOList(List<ArticleVO> articleVOList) {
        for (ArticleVO articleVO : articleVOList) {
            setCommentCountByArticleVO(articleVO);
        }
    }

    @Override
    public ArticleVO getArticleById(Long id) {
        Article article = baseMapper.selectById(id);
        if (Objects.isNull(article)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        ArticleVO articleVO = ArticleConvert.INSTANCE.toVO(article);
        // 设置分类、标签和用户信息
        setCategoryByArticleVO(articleVO);
        setTagByArticleVO(articleVO);
        setUserInfoByArticleVO(articleVO);
        setCommentCountByArticleVO(articleVO);

        // 修改文章点击次数
        incrementClickCount(id);
        return articleVO;
    }

    @Override
    public void incrementClickCount(Long id) {
        // todo 后续增加ip限制，每个ip每天只计算一次点击次数
        LambdaUpdateWrapper<Article> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.setSql(" click_count = click_count + 1 ").eq(Article::getId, id);
        baseMapper.update(updateWrapper);
    }

    @Override
    public long getArticleCount() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 文章为公开状态
        queryWrapper.eq(Article::getIsPublish, StatusConstant.ON)
                .eq(Article::getAuditStatus, StatusConstant.ON);
        return baseMapper.selectCount(queryWrapper);
    }

}