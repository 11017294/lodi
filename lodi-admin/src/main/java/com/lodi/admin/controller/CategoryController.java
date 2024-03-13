package com.lodi.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.web.domain.Result;
import com.lodi.common.model.convert.category.CategoryConvert;
import com.lodi.common.model.entity.Category;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.category.CategoryAddRequest;
import com.lodi.common.model.request.category.CategoryPageRequest;
import com.lodi.common.model.request.category.CategoryUpdateRequest;
import com.lodi.common.model.vo.CategoryVO;
import com.lodi.xo.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.lodi.common.core.enums.ErrorCode.NOT_FOUND_ERROR;

/**
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Tag(name = "文章类别", description = "文章类别")
@RestController
@RequestMapping("category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Operation(summary = "获取文章类别分页")
    @GetMapping("page")
    public Result<Page<CategoryVO>> getCategoryPage(@ParameterObject @Validated CategoryPageRequest pageRequest) {
        Page<Category> categoryPage = categoryService.getCategoryPage(pageRequest);
        return Result.success(CategoryConvert.INSTANCE.toVO(categoryPage));
    }

    @Operation(summary = "获取所有文章列表")
    @GetMapping("list")
    public Result<List<CategoryVO>> getCategoryList() {
        List<Category> categorieList = categoryService.list();
        return Result.success(CategoryConvert.INSTANCE.toVO(categorieList));
    }

    @Operation(summary = "获取文章类别")
    @GetMapping("get")
    public Result<CategoryVO> getCategory(@ParameterObject @Validated IdRequest request) {
        Category category = categoryService.getById(request.getId());
        if (category == null) {
            throw new BusinessException(NOT_FOUND_ERROR);
        }
        return Result.success(CategoryConvert.INSTANCE.toVO(category));
    }

    @Operation(summary = "新增文章类别")
    @PostMapping("add")
    public Result<Boolean> addCategory(@RequestBody @Validated CategoryAddRequest addRequest) {
        return Result.success(categoryService.insertCategory(addRequest));
    }

    @Operation(summary = "更新文章类别")
    @PutMapping("update")
    public Result<Boolean> updateCategory(@RequestBody @Validated CategoryUpdateRequest updateRequest) {
        return Result.success(categoryService.updateCategory(updateRequest));
    }

    @Operation(summary = "删除文章类别")
    @DeleteMapping("delete")
    public Result<Boolean> deleteCategory(@RequestBody @Validated IdRequest request) {
        return Result.success(categoryService.deleteCategory(request.getId()));
    }
}
