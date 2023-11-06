package com.lodi.xo.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.service.BaseService;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.request.article.ArticleAddRequest;
import com.lodi.common.model.request.article.ArticlePageRequest;
import com.lodi.common.model.request.article.ArticleUpdateRequest;
import com.lodi.common.model.request.article.AuditArticleRequest;

/**
 * 文章 服务层
 * @author MaybeBin
 * @createDate 2023-11-01
 */
public interface ArticleService extends BaseService<Article> {

    Boolean insertArticle(ArticleAddRequest articleAddRequest);

    Boolean updateArticle(ArticleUpdateRequest articleUpdateRequest);

    Boolean deleteArticle(Long id);

    Page<Article> getArticlePage(ArticlePageRequest articlePageRequest);

    Boolean auditArticle(AuditArticleRequest auditArticleRequest);
}
