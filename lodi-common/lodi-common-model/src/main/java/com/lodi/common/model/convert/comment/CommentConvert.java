package com.lodi.common.model.convert.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.Comment;
import com.lodi.common.model.vo.CommentTreeVO;
import com.lodi.common.model.vo.CommentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.lodi.common.model.request.comment.CommentAddRequest;
import com.lodi.common.model.request.comment.CommentUpdateRequest;

import java.util.List;

/**
 * 评论 Convert
 *
 * @author MaybeBin
 * @createDate 2024-04-03
 */
@Mapper
public interface CommentConvert {

    CommentConvert INSTANCE = Mappers.getMapper(CommentConvert.class);

    Comment toEntity(CommentAddRequest addRequest);

    Comment toEntity(CommentUpdateRequest updateRequest);

    CommentVO toVO(Comment comment);
    
    List<CommentVO> toVO(List<Comment> commentList); 
    
    Page<CommentVO> toVO(Page<Comment> commentPage);

    List<CommentTreeVO> toTreeVO(List<Comment> commentList);

    Page<CommentTreeVO> toTreeVO(Page<Comment> commentPage);
}