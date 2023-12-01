package com.lodi.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.web.domain.Result;
import com.lodi.common.model.convert.category.CategoryConvert;
import com.lodi.common.model.entity.Category;
import com.lodi.common.model.vo.ArticleVO;
import com.lodi.common.model.vo.CategoryVO;
import com.lodi.xo.service.ArticleService;
import com.lodi.xo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MaybeBin
 * @createDate 2023-11-30
 */
@Slf4j
@Tag(name = "文章类别相关接口")
@RestController
@RequestMapping("category")
public class CategoryController {

    @Resource
    private ArticleService articleService;
    @Resource
    private CategoryService categoryService;

    @Operation(summary = "获取文章类别信息")
    @GetMapping("getCategoryList")
    //获取文章类别列表
    public Result<List<CategoryVO>> getCategoryList() {
        log.info("获取文章类别信息");
        List<Category> tagsList = categoryService.list();
        return Result.success(CategoryConvert.INSTANCE.toVO(tagsList));
    }

    @Operation(summary = "根据分类获取文章列表")
    @GetMapping("getArticleByCategoryId")
    public Result<Page<ArticleVO>> getArticleByCategoryId(@RequestParam("currentPage") Long currentPage, @RequestParam("categoryId") Long categoryId) {
        log.info("根据分类获取文章列表");
        Page<ArticleVO> articlePage = articleService.getArticleByCategoryId(currentPage, categoryId);
        return Result.success(articlePage);
    }

}
