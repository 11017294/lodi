package com.lodi.xo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.service.impl.BaseServiceImpl;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.request.article.ArticleAddRequest;
import com.lodi.common.model.request.article.ArticlePageRequest;
import com.lodi.common.model.request.article.ArticleUpdateRequest;
import com.lodi.common.model.request.article.AuditArticleRequest;
import com.lodi.xo.mapper.ArticleMapper;
import com.lodi.xo.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 文章 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public Boolean insertArticle(ArticleAddRequest articleAddRequest) {
        // 获取当前用户信息

        // 判断标签id、类别id 是否存在

        Article article = new Article();
        BeanUtils.copyProperties(articleAddRequest, article);

        // todo 临时设置
        article.setUserId(1L);

        return save(article);
    }

    @Override
    public Boolean updateArticle(ArticleUpdateRequest articleUpdateRequest) {
        // 判断是否当前用户或管理员

        // 判断标签id、类别id 是否存在

        Article article = new Article();
        BeanUtils.copyProperties(articleUpdateRequest, article);
        return updateById(article);
    }

    @Override
    public Boolean deleteArticle(Long id) {
        // 判断是否当前用户或管理员

        return removeById(id);
    }

    @Override
    public Page<Article> getArticlePage(ArticlePageRequest articlePageRequest) {
        Page<Article> page = new Page<>(articlePageRequest.getCurrent(), articlePageRequest.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = buildQueryWrapper(articlePageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<Article> buildQueryWrapper(ArticlePageRequest articlePageRequest) {
        return new LambdaQueryWrapper<Article>()
                .like(StringUtils.isNotBlank(articlePageRequest.getTitle()),
                        Article::getTitle, articlePageRequest.getTitle())
                .like(StringUtils.isNotBlank(articlePageRequest.getSummary()),
                        Article::getSummary, articlePageRequest.getSummary())
                .like(StringUtils.isNotBlank(articlePageRequest.getContent()),
                        Article::getContent, articlePageRequest.getContent())
                .eq(Objects.nonNull(articlePageRequest.getUserId()),
                        Article::getUserId, articlePageRequest.getUserId())
                .eq(Objects.nonNull(articlePageRequest.getCategoryId()),
                        Article::getCategoryId, articlePageRequest.getCategoryId())
                .eq(Objects.nonNull(articlePageRequest.getIsPublish()),
                        Article::getIsPublish, articlePageRequest.getIsPublish())
                .eq(Objects.nonNull(articlePageRequest.getOpenComment()),
                        Article::getOpenComment, articlePageRequest.getOpenComment())
                .eq(Objects.nonNull(articlePageRequest.getVipArticle()),
                        Article::getVipArticle, articlePageRequest.getVipArticle())
                .eq(Objects.nonNull(articlePageRequest.getAuditStatus()),
                        Article::getAuditStatus, articlePageRequest.getAuditStatus());
    }

    @Override
    public Boolean auditArticle(AuditArticleRequest auditArticleRequest) {
        Integer newStatus = auditArticleRequest.getAuditStatus();
        // 检查文章是否存在
        Article article = baseMapper.selectById(auditArticleRequest.getId());
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
        newArticle.setId(auditArticleRequest.getId());
        newArticle.setAuditStatus(newStatus);
        return updateById(newArticle);
    }
}
