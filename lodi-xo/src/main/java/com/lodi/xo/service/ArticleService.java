package com.lodi.xo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.mybatis.service.BaseService;
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
     * @param addRequest
     * @return
     */
    Boolean insertArticle(ArticleAddRequest addRequest);

    /**
     * 修改文章
     *
     * @param updateRequest
     * @return
     */
    Boolean updateArticle(ArticleUpdateRequest updateRequest);

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
     * @param pageRequest
     * @return
     */
    Page<ArticleVO> getArticlePage(ArticlePageRequest pageRequest);

    /**
     * 审核文章
     *
     * @param auditRequest
     * @return
     */
    Boolean auditArticle(AuditArticleRequest auditRequest);

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
     * 根据标签ID获取文章列表
     *
     * @param currentPage
     * @param tagId
     * @return
     */
    Page<ArticleVO> getArticleByTagId(Long currentPage, Long tagId);

    /**
     * 根据分类ID获取文章列表
     *
     * @param currentPage
     * @param categoryId
     * @return
     */
    Page<ArticleVO> getArticleByCategoryId(Long currentPage, Long categoryId);

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

    /**
     * 给文章设置用户信息
     *
     * @param articleVO
     */
    void setUserInfoByArticleVO(ArticleVO articleVO);

    /**
     * 给文章列表设置用户信息
     *
     * @param articleVOList
     */
    void setUserInfoByArticleVOList(List<ArticleVO> articleVOList);

    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    ArticleVO getArticleById(Long id);

    /**
     * 增加点击次数
     *
     * @param id
     */
    void incrementClickCount(Long id);
}
