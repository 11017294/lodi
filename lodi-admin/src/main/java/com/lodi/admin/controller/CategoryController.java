package com.lodi.admin.controller;

import com.lodi.common.model.entity.Category;
import com.lodi.xo.service.CategoryService;
import com.lodi.common.model.request.category.CategoryAddRequest;
import com.lodi.common.model.request.category.CategoryUpdateRequest;
import com.lodi.common.model.request.category.CategoryPageRequest;
import com.lodi.common.model.vo.CategoryVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.web.domain.Result;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.request.IdRequest;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Operation(summary = "获取文章类别分页")
    @GetMapping("page")
    public Result<Page<Category>> getCategoryPage(@ParameterObject CategoryPageRequest pageRequest) {
        return Result.success(categoryService.getCategoryPage(pageRequest));
    }

    @Operation(summary = "获取所有文章列表")
    @GetMapping("/list")
    public Result<List<Category>> getCategoryList() {
        return Result.success(categoryService.list());
    }

    @Operation(summary = "获取文章类别")
    @GetMapping("get")
    public Result<CategoryVO> getCategory(@ParameterObject IdRequest request) {
        Category category = categoryService.getById(request.getId());
        if (category == null) {
            throw new BusinessException(NOT_FOUND_ERROR);
        }
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category, categoryVO);
        return Result.success(categoryVO);
    }

    @Operation(summary = "新增文章类别")
    @PostMapping("add")
    public Result<Boolean> addCategory(@RequestBody CategoryAddRequest addRequest) {
        return Result.success(categoryService.insertCategory(addRequest));
    }

    @Operation(summary = "更新文章类别")
    @PutMapping("update")
    public Result<Boolean> updateCategory(@RequestBody CategoryUpdateRequest updateRequest) {
        return Result.success(categoryService.updateCategory(updateRequest));
    }

    @Operation(summary = "删除文章类别")
    @DeleteMapping("delete")
    public Result<Boolean> deleteCategory(@RequestBody IdRequest request) {
        return Result.success(categoryService.deleteCategory(request.getId()));
    }
}
