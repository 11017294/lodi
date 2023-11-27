package com.lodi.common.model.convert.animeInfo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.AnimeInfo;
import com.lodi.common.model.vo.AnimeInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 动漫信息 Convert
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Mapper
public interface AnimeInfoConvert {

    AnimeInfoConvert INSTANCE = Mappers.getMapper(AnimeInfoConvert.class);

    AnimeInfoVO toVO(AnimeInfo animeInfo);

    List<AnimeInfoVO> toVO(List<AnimeInfo> animeInfos);

    Page<AnimeInfoVO> toVO(Page<AnimeInfo> animeInfoPage);
}
