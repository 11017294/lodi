package com.lodi.xo.mapper;

import com.lodi.common.model.entity.Comment;
import com.lodi.common.mybatis.mapper.BaseMapper;

/**
 * 评论 数据层
 *
 * @author MaybeBin
 * @createDate 2024-04-03
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 删除评论及其子评论
     *
     * @param id 评论id
     * @return 删除的记录数
     */
    int deleteCommentAndChildren(Long id);
}
