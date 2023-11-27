package com.lodi.common.model.convert.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.vo.ArticleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文章 Convert
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Mapper
public interface ArticleConvert {

    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    ArticleVO toVO(Article article);

    List<ArticleVO> toVO(List<Article> articles);

    Page<ArticleVO> toVO(Page<Article> articlePage);

}
