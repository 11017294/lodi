package com.lodi.common.model.convert.tags;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.Tags;
import com.lodi.common.model.request.tags.TagsAddRequest;
import com.lodi.common.model.request.tags.TagsUpdateRequest;
import com.lodi.common.model.vo.TagsVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 标签 Convert
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Mapper
public interface TagsConvert {

    TagsConvert INSTANCE = Mappers.getMapper(TagsConvert.class);

    Tags toEntity(TagsAddRequest addRequest);

    Tags toEntity(TagsUpdateRequest updateRequest);

    TagsVO toVO(Tags tags);

    List<TagsVO> toVO(List<Tags> tagsList);

    Page<TagsVO> toVO(Page<Tags> tagsPage);
}
