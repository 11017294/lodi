package com.lodi.admin.controller;

import com.lodi.common.model.entity.NavCategory;
import com.lodi.xo.service.NavCategoryService;
import com.lodi.common.model.request.navCategory.NavCategoryAddRequest;
import com.lodi.common.model.request.navCategory.NavCategoryUpdateRequest;
import com.lodi.common.model.request.navCategory.NavCategoryPageRequest;
import com.lodi.common.model.convert.navCategory.NavCategoryConvert;
import com.lodi.common.model.vo.NavCategoryVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.domain.Result;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.request.IdRequest;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import static com.lodi.common.core.enums.ErrorCode.NOT_FOUND_ERROR;

/**
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Tag(name = "导航分类表", description = "导航分类表")
@RestController
@RequestMapping("navCategory")
public class NavCategoryController {

    @Resource
    private NavCategoryService navCategoryService;

    @Operation(summary = "获取导航分类表分页")
    @GetMapping("page")
    public Result<Page<NavCategoryVO>> getNavCategoryPage(@ParameterObject NavCategoryPageRequest pageRequest) {
        Page<NavCategory> navCategoryPage = navCategoryService.getNavCategoryPage(pageRequest);
        return Result.success(NavCategoryConvert.INSTANCE.toVO(navCategoryPage));
    }

    @Operation(summary = "获取导航分类表")
    @GetMapping("get")
    public Result<NavCategoryVO> getNavCategory(@ParameterObject IdRequest request) {
        NavCategory navCategory = navCategoryService.getById(request.getId());
        if (navCategory == null) {
            throw new BusinessException(NOT_FOUND_ERROR);
        }
        return Result.success(NavCategoryConvert.INSTANCE.toVO(navCategory));
    }

    @Operation(summary = "新增导航分类表")
    @PostMapping("add")
    public Result<Boolean> addNavCategory(@RequestBody NavCategoryAddRequest addRequest) {
        return Result.success(navCategoryService.insertNavCategory(addRequest));
    }

    @Operation(summary = "更新导航分类表")
    @PutMapping("update")
    public Result<Boolean> updateNavCategory(@RequestBody NavCategoryUpdateRequest updateRequest) {
        return Result.success(navCategoryService.updateNavCategory(updateRequest));
    }

    @Operation(summary = "删除导航分类表")
    @DeleteMapping("delete")
    public Result<Boolean> deleteNavCategory(@RequestBody IdRequest request) {
        return Result.success(navCategoryService.deleteNavCategory(request.getId()));
    }
}
