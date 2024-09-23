package com.lodi.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.domain.Result;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.article.ArticleAddRequest;
import com.lodi.common.model.request.article.ArticleByUserIdRequest;
import com.lodi.common.model.request.article.ArticleUpdateRequest;
import com.lodi.common.model.vo.ArticleVO;
import com.lodi.xo.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.lodi.common.core.constant.StatusConstant.OFF;
import static com.lodi.common.core.constant.StatusConstant.ON;

/**
 * @author MaybeBin
 * @createDate 2023-11-30
 */
@Slf4j
@Tag(name = "文章相关接口")
@RestController
@AllArgsConstructor
@RequestMapping("article")
public class ArticleController {

    private final ArticleService articleService;

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

    @Operation(summary = "按用户id获取文章列表")
    @GetMapping("getArticleByUserId")
    public Result<Page<ArticleVO>> getArticleByUserId(@ParameterObject @Validated ArticleByUserIdRequest request) {
        return Result.success(articleService.getArticleByUserId(request));
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

}
