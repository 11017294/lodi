package com.lodi.xo.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.service.BaseService;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.request.article.ArticleAddRequest;
import com.lodi.common.model.request.article.ArticlePageRequest;
import com.lodi.common.model.request.article.ArticleUpdateRequest;
import com.lodi.common.model.request.article.AuditArticleRequest;
import com.lodi.common.model.vo.ArticleVO;

import java.util.List;

/**
 * 文章 服务层
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
public interface ArticleService extends BaseService<Article> {

    /**
     * 新增文章
     *
     * @param articleAddRequest
     * @return
     */
    Boolean insertArticle(ArticleAddRequest articleAddRequest);

    /**
     * 修改文章
     *
     * @param articleUpdateRequest
     * @return
     */
    Boolean updateArticle(ArticleUpdateRequest articleUpdateRequest);

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    Boolean deleteArticle(Long id);

    /**
     * 分页获取文章
     *
     * @param articlePageRequest
     * @return
     */
    Page<ArticleVO> getArticlePage(ArticlePageRequest articlePageRequest);

    /**
     * 审核文章
     *
     * @param auditArticleRequest
     * @return
     */
    Boolean auditArticle(AuditArticleRequest auditArticleRequest);

    /**
     * 按字段排序获取文章（倒序）
     *
     * @param currentPage 当前页号
     * @param column      字段名
     * @return
     */
    Page<ArticleVO> getArticleByOrderDesc(Long currentPage, String column);

    /**
     * 按搜索获取文章
     *
     * @param currentPage 当前页号
     * @param keyword     搜索关键字
     * @return
     */
    Page<ArticleVO> getArticleBySearch(Long currentPage, String keyword);

    /**
     * 给文章设置标签
     *
     * @param articleVO
     */
    void setTagByArticleVO(ArticleVO articleVO);

    /**
     * 给文章列表设置标签
     *
     * @param articleVOList
     */
    void setTagByArticleVOList(List<ArticleVO> articleVOList);

    /**
     * 给文章设置分类
     *
     * @param articleVO
     */
    void setCategoryByArticleVO(ArticleVO articleVO);

    /**
     * 给文章列表设置分类
     *
     * @param articleVOList
     */
    void setCategoryByArticleVOList(List<ArticleVO> articleVOList);

}
