package com.lodi.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.web.domain.Result;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.article.ArticleAddRequest;
import com.lodi.common.model.request.article.ArticlePageRequest;
import com.lodi.common.model.request.article.ArticleUpdateRequest;
import com.lodi.common.model.request.article.AuditArticleRequest;
import com.lodi.common.model.vo.ArticleVO;
import com.lodi.common.security.annotation.RequiresRoles;
import com.lodi.xo.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author MaybeBin
 * @createDate 2023-09-22
 */
@Tag(name = "文章管理", description = "文章管理")
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Operation(summary = "获取文章分页")
    @GetMapping("page")
    public Result<Page<Article>> getArticlePage(ArticlePageRequest articlePageRequest) {
        return Result.success(articleService.getArticlePage(articlePageRequest));
    }

    @Operation(summary = "获取文章信息")
    @GetMapping("get")
    public Result<ArticleVO> getArticle(IdRequest idRequest) {
        Article article = articleService.getById(idRequest.getId());
        if (article == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article, articleVO);
        return Result.success(articleVO);
    }

    @Operation(summary = "新增文章")
    @PostMapping("add")
    public Result<Boolean> addArticle(@RequestBody ArticleAddRequest articleAddRequest) {
        return Result.success(articleService.insertArticle(articleAddRequest));
    }

    @Operation(summary = "更新文章")
    @PostMapping(value = "update")
    public Result<Boolean> updateArticle(@RequestBody ArticleUpdateRequest articleUpdateRequest) {
        return Result.success(articleService.updateArticle(articleUpdateRequest));
    }

    @Operation(summary = "删除文章")
    @PostMapping("delete")
    public Result<Boolean> deleteArticle(@RequestBody IdRequest request) {
        return Result.success(articleService.deleteArticle(request.getId()));
    }

    @RequiresRoles("admin")
    @Operation(summary = "批量删除文章")
    @PostMapping("deleteBatch")
    public Result<Boolean> deleteBatchArticle(@RequestBody Long[] ids) {
        return Result.success(articleService.removeByIds(Arrays.asList(ids)));
    }

    @RequiresRoles("admin")
    @Operation(summary = "审核")
    @PostMapping("audit")
    public Result<Boolean> auditArticle(@RequestBody AuditArticleRequest auditArticleRequest) {
        return Result.success(articleService.auditArticle(auditArticleRequest));
    }

}
