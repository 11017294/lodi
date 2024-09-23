package com.lodi.xo.mapper;

import com.lodi.common.model.entity.Article;
import com.lodi.common.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 文章表 数据层
 *
 * @author MaybeBin
 * @createDate 2023-11-01
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 获取文章贡献度
     *
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    @Select(" select date_format(create_time, '%Y-%m-%d') dateDate, count(1) count " +
            " FROM t_article " +
            " where is_delete = 0 and create_time between #{startDateTime} and #{endDateTime} " +
            " GROUP BY date_format(create_time, '%Y-%m-%d') ")
    List<Map<String, Object>> getArticleContributeCount(@Param("startDateTime") LocalDateTime startDateTime,
                                                        @Param("endDateTime") LocalDateTime endDateTime);
}
