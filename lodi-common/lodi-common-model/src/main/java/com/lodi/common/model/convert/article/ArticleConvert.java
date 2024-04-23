package com.lodi.common.model.convert.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.request.article.ArticleAddRequest;
import com.lodi.common.model.request.article.ArticleUpdateRequest;
import com.lodi.common.model.vo.ArticleVO;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章 Convert
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Mapper
public interface ArticleConvert {

    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    @Mapping(target = "tagsId", source = "tags")
    Article toEntity(ArticleAddRequest addRequest);

    @Mapping(target = "tagsId", source = "tags")
    Article toEntity(ArticleUpdateRequest updateRequest);

    @Mapping(target = "tags", source = "tagsId")
    ArticleVO toVO(Article article);

    List<ArticleVO> toVO(List<Article> articles);

    Page<ArticleVO> toVO(Page<Article> articlePage);

    default String tagsToTagsId(List<Long> tags) {
        if (CollectionUtils.isEmpty(tags)) {
            return new String();
        }
        return tags.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    default List<Long> tagsIdToTags(String tagsId) {
        if (StringUtils.isBlank(tagsId)) {
            return null;
        }
        String[] tagsIdArray = tagsId.split(",");
        return Arrays.stream(tagsIdArray).map(Long::valueOf).collect(Collectors.toList());
    }

}
