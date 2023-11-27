package com.lodi.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.core.web.domain.Result;
import com.lodi.common.model.convert.animeInfo.AnimeInfoConvert;
import com.lodi.common.model.entity.AnimeInfo;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.animeInfo.AnimeInfoAddRequest;
import com.lodi.common.model.request.animeInfo.AnimeInfoPageRequest;
import com.lodi.common.model.request.animeInfo.AnimeInfoUpdateRequest;
import com.lodi.common.model.vo.AnimeInfoVO;
import com.lodi.xo.service.AnimeInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.lodi.common.core.enums.ErrorCode.NOT_FOUND_ERROR;

/**
 * @author MaybeBin
 * @createDate 2023-11-20
 */
@Tag(name = "动漫信息", description = "动漫信息")
@RestController
@RequestMapping("/animeInfo")
public class AnimeInfoController {

    @Resource
    private AnimeInfoService animeInfoService;

    @Operation(summary = "获取动漫信息分页")
    @GetMapping("page")
    public Result<Page<AnimeInfoVO>> getAnimeInfoPage(@ParameterObject AnimeInfoPageRequest pageRequest) {
        Page<AnimeInfo> animeInfoPage = animeInfoService.getAnimeInfoPage(pageRequest);
        return Result.success(AnimeInfoConvert.INSTANCE.toVO(animeInfoPage));
    }

    @Operation(summary = "获取动漫信息")
    @GetMapping("get")
    public Result<AnimeInfoVO> getAnimeInfo(@ParameterObject IdRequest request) {
        AnimeInfo animeInfo = animeInfoService.getById(request.getId());
        if (animeInfo == null) {
            throw new BusinessException(NOT_FOUND_ERROR);
        }
        return Result.success(AnimeInfoConvert.INSTANCE.toVO(animeInfo));
    }

    @Operation(summary = "根据系列ID获取动漫信息")
    @GetMapping("getAnimeInfoBySeriesId")
    public Result<List<AnimeInfoVO>> getAnimeInfoBySeriesId(@ParameterObject IdRequest request) {
        List<AnimeInfo> animeInfos = animeInfoService.getAnimeInfoBySeriesId(request.getId());
        if (animeInfos == null) {
            throw new BusinessException(NOT_FOUND_ERROR);
        }
        return Result.success(AnimeInfoConvert.INSTANCE.toVO(animeInfos));
    }

    @Operation(summary = "新增动漫信息")
    @PostMapping("add")
    public Result<Boolean> addAnimeInfo(@RequestBody AnimeInfoAddRequest addRequest) {
        return Result.success(animeInfoService.insertAnimeInfo(addRequest));
    }

    @Operation(summary = "更新动漫信息")
    @PutMapping("update")
    public Result<Boolean> updateAnimeInfo(@RequestBody AnimeInfoUpdateRequest updateRequest) {
        return Result.success(animeInfoService.updateAnimeInfo(updateRequest));
    }

    @Operation(summary = "删除动漫信息")
    @DeleteMapping("delete")
    public Result<Boolean> deleteAnimeInfo(@RequestBody IdRequest request) {
        return Result.success(animeInfoService.deleteAnimeInfo(request.getId()));
    }
}
