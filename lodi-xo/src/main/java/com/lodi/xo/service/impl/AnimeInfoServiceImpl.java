package com.lodi.xo.service.impl;

import com.lodi.common.model.entity.AnimeInfo;
import com.lodi.xo.mapper.AnimeInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import com.lodi.common.model.request.animeInfo.AnimeInfoAddRequest;
import com.lodi.common.model.request.animeInfo.AnimeInfoUpdateRequest;
import com.lodi.common.model.request.animeInfo.AnimeInfoPageRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.xo.service.AnimeInfoService;
import com.lodi.common.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 动漫信息 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-11-20
 */
@Service
public class AnimeInfoServiceImpl extends BaseServiceImpl<AnimeInfoMapper, AnimeInfo> implements AnimeInfoService {

    @Override
    public Boolean insertAnimeInfo(AnimeInfoAddRequest addRequest) {
        AnimeInfo animeInfo = new AnimeInfo();
        BeanUtils.copyProperties(addRequest, animeInfo);
        return updateById(animeInfo);
    }

    @Override
    public Boolean updateAnimeInfo(AnimeInfoUpdateRequest updateRequest) {
        AnimeInfo animeInfo = new AnimeInfo();
        BeanUtils.copyProperties(updateRequest, animeInfo);
        return updateById(animeInfo);
    }

    @Override
    public Boolean deleteAnimeInfo(Long id) {
        return removeById(id);
    }

    @Override
    public Page<AnimeInfo> getAnimeInfoPage(AnimeInfoPageRequest pageRequest) {
        Page<AnimeInfo> page = new Page<>(pageRequest.getCurrent(), pageRequest.getPageSize());
        LambdaQueryWrapper<AnimeInfo> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<AnimeInfo> buildQueryWrapper(AnimeInfoPageRequest pageRequest) {
        return new LambdaQueryWrapper<AnimeInfo>();
    }

    @Override
    public List<AnimeInfo> getAnimeInfoBySeriesId(Long seriesId) {
        LambdaQueryWrapper<AnimeInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AnimeInfo::getSeriesId, seriesId);
        return baseMapper.selectList(queryWrapper);
    }
}
