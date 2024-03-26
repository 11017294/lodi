package com.lodi.web.controller;

import com.lodi.common.core.domain.Result;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.article.ArticleAddRequest;
import com.lodi.common.model.request.article.ArticleUpdateRequest;
import com.lodi.xo.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author MaybeBin
 * @createDate 2023-11-30
 */
@Slf4j
@Tag(name = "文章相关接口")
@RestController
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

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

}
