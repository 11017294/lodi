package com.lodi.common.model.convert.animeInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.AnimeInfo;
import com.lodi.common.model.vo.AnimeInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.lodi.common.model.request.animeInfo.AnimeInfoAddRequest;
import com.lodi.common.model.request.animeInfo.AnimeInfoUpdateRequest;

import java.util.List;

/**
 * 动漫信息 Convert
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Mapper
public interface AnimeInfoConvert {

    AnimeInfoConvert INSTANCE = Mappers.getMapper(AnimeInfoConvert.class);

    AnimeInfo toEntity(AnimeInfoAddRequest addRequest);

    AnimeInfo toEntity(AnimeInfoUpdateRequest updateRequest);

    AnimeInfoVO toVO(AnimeInfo animeInfo);
    
    List<AnimeInfoVO> toVO(List<AnimeInfo> animeInfoList); 
    
    Page<AnimeInfoVO> toVO(Page<AnimeInfo> animeInfoPage);
}