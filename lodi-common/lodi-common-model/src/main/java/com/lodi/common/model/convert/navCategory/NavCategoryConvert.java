package com.lodi.common.model.convert.navCategory;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.NavCategory;
import com.lodi.common.model.vo.NavCategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.lodi.common.model.request.navCategory.NavCategoryAddRequest;
import com.lodi.common.model.request.navCategory.NavCategoryUpdateRequest;

import java.util.List;

/**
 * 导航分类表 Convert
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Mapper
public interface NavCategoryConvert {

    NavCategoryConvert INSTANCE = Mappers.getMapper(NavCategoryConvert.class);

    NavCategory toEntity(NavCategoryAddRequest addRequest);

    NavCategory toEntity(NavCategoryUpdateRequest updateRequest);

    NavCategoryVO toVO(NavCategory navCategory);
    
    List<NavCategoryVO> toVO(List<NavCategory> navCategoryList); 
    
    Page<NavCategoryVO> toVO(Page<NavCategory> navCategoryPage);
}