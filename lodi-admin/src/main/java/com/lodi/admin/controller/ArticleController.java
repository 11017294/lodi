package com.lodi.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.web.domain.Result;
import com.lodi.common.model.convert.article.ArticleConvert;
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
import org.springdoc.api.annotations.ParameterObject;
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
    public Result<Page<ArticleVO>> getArticlePage(@ParameterObject ArticlePageRequest pageRequest) {
        return Result.success(articleService.getArticlePage(pageRequest));
    }

    @Operation(summary = "获取文章信息")
    @GetMapping("get")
    public Result<ArticleVO> getArticle(@ParameterObject IdRequest idRequest) {
        Article article = articleService.getById(idRequest.getId());
        if (article == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return Result.success(ArticleConvert.INSTANCE.toVO(article));
    }

    @Operation(summary = "新增文章")
    @PostMapping("add")
    public Result<Boolean> addArticle(@RequestBody ArticleAddRequest addRequest) {
        return Result.success(articleService.insertArticle(addRequest));
    }

    @Operation(summary = "更新文章")
    @PutMapping("update")
    public Result<Boolean> updateArticle(@RequestBody ArticleUpdateRequest updateRequest) {
        return Result.success(articleService.updateArticle(updateRequest));
    }

    @Operation(summary = "删除文章")
    @DeleteMapping("delete")
    public Result<Boolean> deleteArticle(@RequestBody IdRequest request) {
        return Result.success(articleService.deleteArticle(request.getId()));
    }

    @RequiresRoles("admin")
    @Operation(summary = "批量删除文章")
    @DeleteMapping("deleteBatch")
    public Result<Boolean> deleteBatchArticle(@RequestBody Long[] ids) {
        return Result.success(articleService.removeByIds(Arrays.asList(ids)));
    }

    @RequiresRoles("admin")
    @Operation(summary = "审核")
    @PutMapping("audit")
    public Result<Boolean> auditArticle(@RequestBody AuditArticleRequest auditRequest) {
        return Result.success(articleService.auditArticle(auditRequest));
    }

}
