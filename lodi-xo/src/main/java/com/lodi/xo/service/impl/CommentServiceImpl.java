package com.lodi.xo.service.impl;

import com.lodi.common.model.entity.Comment;
import com.lodi.xo.mapper.CommentMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lodi.common.model.request.comment.CommentAddRequest;
import com.lodi.common.model.request.comment.CommentUpdateRequest;
import com.lodi.common.model.request.comment.CommentPageRequest;
import com.lodi.common.model.convert.comment.CommentConvert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.xo.service.CommentService;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 评论 服务层实现
 *
 * @author MaybeBin
 * @createDate 2024-04-03
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public Boolean insertComment(CommentAddRequest addRequest) {
        Comment comment = CommentConvert.INSTANCE.toEntity(addRequest);
        return save(comment);
    }

    @Override
    public Boolean updateComment(CommentUpdateRequest updateRequest) {
        Comment comment = CommentConvert.INSTANCE.toEntity(updateRequest);
        return updateById(comment);
    }

    @Override
    public Boolean deleteComment(Long id) {
        return removeById(id);
    }

    @Override
    public Page<Comment> getCommentPage(CommentPageRequest pageRequest) {
        Page<Comment> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<Comment> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<Comment> buildQueryWrapper(CommentPageRequest pageRequest) {
        return new LambdaQueryWrapper<Comment>();
    }
}
