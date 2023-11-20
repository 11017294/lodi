package com.lodi.xo.service;

import com.lodi.common.model.entity.AnimeInfo;
import com.lodi.common.core.service.BaseService;
import com.lodi.common.model.request.animeInfo.AnimeInfoAddRequest;
import com.lodi.common.model.request.animeInfo.AnimeInfoUpdateRequest;
import com.lodi.common.model.request.animeInfo.AnimeInfoPageRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 动漫信息 服务层
 *
 * @author MaybeBin
 * @createDate 2023-11-20
 */
public interface AnimeInfoService extends BaseService<AnimeInfo> {

    /**
     * 新增动漫信息
     * @param addRequest 新增请求体
     * @return
     */
    Boolean insertAnimeInfo(AnimeInfoAddRequest addRequest);

    /**
     * 更新动漫信息
     * @param updateRequest 更新请求体
     * @return
     */
    Boolean updateAnimeInfo(AnimeInfoUpdateRequest updateRequest);

    /**
     * 删除动漫信息
     * @param id 动漫信息ID
     * @return
     */
    Boolean deleteAnimeInfo(Long id);

    /**
     * 获取动漫信息
     * @param pageRequest 分页查询请求体
     * @return
     */
    Page<AnimeInfo> getAnimeInfoPage(AnimeInfoPageRequest pageRequest);

    /**
     * 根据系列ID获取动漫信息
     * @param seriesId 系列ID
     * @return
     */
    List<AnimeInfo> getAnimeInfoBySeriesId(Long seriesId);
}
