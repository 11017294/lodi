package com.lodi.common.model.convert.navigate;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.Navigate;
import com.lodi.common.model.vo.NavigateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.lodi.common.model.request.navigate.NavigateAddRequest;
import com.lodi.common.model.request.navigate.NavigateUpdateRequest;

import java.util.List;

/**
 * 导航 Convert
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Mapper
public interface NavigateConvert {

    NavigateConvert INSTANCE = Mappers.getMapper(NavigateConvert.class);

    Navigate toEntity(NavigateAddRequest addRequest);

    Navigate toEntity(NavigateUpdateRequest updateRequest);

    NavigateVO toVO(Navigate navigate);
    
    List<NavigateVO> toVO(List<Navigate> navigateList); 
    
    Page<NavigateVO> toVO(Page<Navigate> navigatePage);
}