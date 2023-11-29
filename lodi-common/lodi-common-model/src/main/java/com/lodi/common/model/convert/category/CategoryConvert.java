package com.lodi.common.model.convert.category;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.Category;
import com.lodi.common.model.request.category.CategoryAddRequest;
import com.lodi.common.model.request.category.CategoryUpdateRequest;
import com.lodi.common.model.vo.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章类别 Convert
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Mapper
public interface CategoryConvert {

    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    Category toEntity(CategoryAddRequest addRequest);

    Category toEntity(CategoryUpdateRequest updateRequest);

    CategoryVO toVO(Category category);

    List<CategoryVO> toVO(List<Category> categoryList);

    Page<CategoryVO> toVO(Page<Category> categoryPage);
}
