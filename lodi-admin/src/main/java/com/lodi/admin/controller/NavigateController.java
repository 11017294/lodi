package com.lodi.admin.controller;

import com.lodi.common.model.entity.Navigate;
import com.lodi.xo.service.NavigateService;
import com.lodi.common.model.request.navigate.NavigateAddRequest;
import com.lodi.common.model.request.navigate.NavigateUpdateRequest;
import com.lodi.common.model.request.navigate.NavigatePageRequest;
import com.lodi.common.model.convert.navigate.NavigateConvert;
import com.lodi.common.model.vo.NavigateVO;
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
@Tag(name = "导航", description = "导航")
@RestController
@RequestMapping("navigate")
public class NavigateController {

    @Resource
    private NavigateService navigateService;

    @Operation(summary = "获取导航分页")
    @GetMapping("page")
    public Result<Page<NavigateVO>> getNavigatePage(@ParameterObject NavigatePageRequest pageRequest) {
        Page<Navigate> navigatePage = navigateService.getNavigatePage(pageRequest);
        return Result.success(NavigateConvert.INSTANCE.toVO(navigatePage));
    }

    @Operation(summary = "获取导航")
    @GetMapping("get")
    public Result<NavigateVO> getNavigate(@ParameterObject IdRequest request) {
        Navigate navigate = navigateService.getById(request.getId());
        if (navigate == null) {
            throw new BusinessException(NOT_FOUND_ERROR);
        }
        return Result.success(NavigateConvert.INSTANCE.toVO(navigate));
    }

    @Operation(summary = "新增导航")
    @PostMapping("add")
    public Result<Boolean> addNavigate(@RequestBody NavigateAddRequest addRequest) {
        return Result.success(navigateService.insertNavigate(addRequest));
    }

    @Operation(summary = "更新导航")
    @PutMapping("update")
    public Result<Boolean> updateNavigate(@RequestBody NavigateUpdateRequest updateRequest) {
        return Result.success(navigateService.updateNavigate(updateRequest));
    }

    @Operation(summary = "删除导航")
    @DeleteMapping("delete")
    public Result<Boolean> deleteNavigate(@RequestBody IdRequest request) {
        return Result.success(navigateService.deleteNavigate(request.getId()));
    }
}
