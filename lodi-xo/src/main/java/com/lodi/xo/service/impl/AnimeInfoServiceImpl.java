package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import com.lodi.common.model.convert.animeInfo.AnimeInfoConvert;
import com.lodi.common.model.entity.AnimeInfo;
import com.lodi.common.model.request.animeInfo.AnimeInfoAddRequest;
import com.lodi.common.model.request.animeInfo.AnimeInfoPageRequest;
import com.lodi.common.model.request.animeInfo.AnimeInfoUpdateRequest;
import com.lodi.xo.mapper.AnimeInfoMapper;
import com.lodi.xo.service.AnimeInfoService;
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
        AnimeInfo animeInfo = AnimeInfoConvert.INSTANCE.toEntity(addRequest);
        return updateById(animeInfo);
    }

    @Override
    public Boolean updateAnimeInfo(AnimeInfoUpdateRequest updateRequest) {
        AnimeInfo animeInfo = AnimeInfoConvert.INSTANCE.toEntity(updateRequest);
        return updateById(animeInfo);
    }

    @Override
    public Boolean deleteAnimeInfo(Long id) {
        return removeById(id);
    }

    @Override
    public Page<AnimeInfo> getAnimeInfoPage(AnimeInfoPageRequest pageRequest) {
        Page<AnimeInfo> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
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
