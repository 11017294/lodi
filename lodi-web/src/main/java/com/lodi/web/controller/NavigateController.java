package com.lodi.web.controller;

import com.lodi.common.core.constant.RedisKeyConstants;
import com.lodi.common.core.domain.Result;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.convert.navCategory.NavCategoryConvert;
import com.lodi.common.model.convert.navigate.NavigateConvert;
import com.lodi.common.model.entity.NavCategory;
import com.lodi.common.model.entity.Navigate;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.navigate.NavigateQueryRequest;
import com.lodi.common.model.vo.NavCategoryVO;
import com.lodi.common.model.vo.NavigateVO;
import com.lodi.xo.service.NavCategoryService;
import com.lodi.xo.service.NavigateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.lodi.common.core.enums.ErrorCode.NOT_FOUND_ERROR;

/**
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Tag(name = "导航")
@RestController
@AllArgsConstructor
@RequestMapping("navigate")
public class NavigateController {

    private final NavigateService navigateService;
    private final NavCategoryService navCategoryService;

    @Operation(summary = "获取导航")
    @GetMapping("list")
    public Result<List<NavigateVO>> getList(@ParameterObject NavigateQueryRequest queryRequest) {
        List<Navigate> navigateList = navigateService.getList(queryRequest);
        return Result.success(NavigateConvert.INSTANCE.toVO(navigateList));
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

    @Operation(summary = "获取导航分类")
    @GetMapping("getCategoryList")
    @Cacheable(cacheNames = RedisKeyConstants.NAV_CATEGORY_LIST)
    public Result<List<NavCategoryVO>> getCategoryList() {
        List<NavCategory> list = navCategoryService.list();
        return Result.success(NavCategoryConvert.INSTANCE.toVO(list));
    }

}
