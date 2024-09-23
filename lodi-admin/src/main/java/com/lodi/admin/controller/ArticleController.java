package com.lodi.admin.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.domain.Result;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.convert.article.ArticleConvert;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.article.ArticleAddRequest;
import com.lodi.common.model.request.article.ArticlePageRequest;
import com.lodi.common.model.request.article.ArticleUpdateRequest;
import com.lodi.common.model.request.article.AuditArticleRequest;
import com.lodi.common.model.vo.ArticleVO;
import com.lodi.xo.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static com.lodi.common.core.constant.StatusConstant.OFF;
import static com.lodi.common.core.constant.StatusConstant.ON;

/**
 * @author MaybeBin
 * @createDate 2023-09-22
 */
@Tag(name = "文章管理", description = "文章管理")
@RestController
@AllArgsConstructor
@RequestMapping("article")
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "获取文章分页")
    @GetMapping("page")
    public Result<Page<ArticleVO>> getArticlePage(@ParameterObject @Validated ArticlePageRequest pageRequest) {
        return Result.success(articleService.getArticlePage(pageRequest));
    }

    @Operation(summary = "获取文章信息")
    @GetMapping("get")
    public Result<ArticleVO> getArticle(@ParameterObject @Validated IdRequest idRequest) {
        Article article = articleService.getById(idRequest.getId());
        if (article == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return Result.success(ArticleConvert.INSTANCE.toVO(article));
    }

    @Operation(summary = "新增文章")
    @PostMapping("add")
    public Result<Boolean> addArticle(@RequestBody @Validated ArticleAddRequest addRequest) {
        return Result.success(articleService.insertArticle(addRequest));
    }

    @Operation(summary = "更新文章")
    @PutMapping("update")
    public Result<Boolean> updateArticle(@RequestBody @Validated ArticleUpdateRequest updateRequest) {
        return Result.success(articleService.updateArticle(updateRequest));
    }

    @Operation(summary = "删除文章")
    @DeleteMapping("delete")
    public Result<Boolean> deleteArticle(@RequestBody @Validated IdRequest request) {
        return Result.success(articleService.deleteArticle(request.getId()));
    }

    @SaCheckRole("admin")
    @Operation(summary = "批量删除文章")
    @DeleteMapping("deleteBatch")
    public Result<Boolean> deleteBatchArticle(@RequestBody Long[] ids) {
        return Result.success(articleService.removeByIds(Arrays.asList(ids)));
    }

    @SaCheckRole("admin")
    @Operation(summary = "审核")
    @PutMapping("audit")
    public Result<Boolean> auditArticle(@RequestBody @Validated AuditArticleRequest auditRequest) {
        return Result.success(articleService.auditArticle(auditRequest));
    }

    @Operation(summary = "发布文章")
    @PostMapping("publish")
    public Result<Boolean> publishArticle(@RequestBody @Validated IdRequest request) {
        return Result.success(articleService.togglePublishStatus(request.getId(), ON));
    }

    @Operation(summary = "取消发布文章")
    @PostMapping("cancelPublish")
    public Result<Boolean> cancelPublishArticle(@RequestBody @Validated IdRequest request) {
        return Result.success(articleService.togglePublishStatus(request.getId(), OFF));
    }

    @Operation(summary = "开启评论")
    @PostMapping("openComment")
    public Result<Boolean> openComment(@RequestBody @Validated IdRequest request) {
        return Result.success(articleService.toggleCommentStatus(request.getId(), ON));
    }

    @Operation(summary = "关闭评论")
    @PostMapping("closeComment")
    public Result<Boolean> closeComment(@RequestBody @Validated IdRequest request) {
        return Result.success(articleService.toggleCommentStatus(request.getId(), OFF));
    }

}
