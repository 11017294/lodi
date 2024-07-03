package com.lodi.xo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.Comment;
import com.lodi.common.model.request.comment.CommentAddRequest;
import com.lodi.common.model.request.comment.CommentPageRequest;
import com.lodi.common.model.request.comment.CommentQueryRequest;
import com.lodi.common.model.request.comment.CommentUpdateRequest;
import com.lodi.common.model.vo.CommentTreeVO;
import com.lodi.common.model.vo.CommentVO;
import com.lodi.common.mybatis.page.PageRequest;
import com.lodi.common.mybatis.service.BaseService;

/**
 * 评论 服务层
 *
 * @author MaybeBin
 * @createDate 2024-04-03
 */
public interface CommentService extends BaseService<Comment> {

    /**
     * 新增评论
     *
     * @param addRequest 新增请求体
     * @return
     */
    Boolean insertComment(CommentAddRequest addRequest);

    /**
     * 获取当前用户的评论
     *
     * @param pageRequest 分页请求体
     * @return
     */
    Page<CommentVO> getCommentByCurrentUser(PageRequest pageRequest);

    /**
     * 更新评论
     *
     * @param updateRequest 更新请求体
     * @return
     */
    Boolean updateComment(CommentUpdateRequest updateRequest);

    /**
     * 删除评论
     *
     * @param id 评论ID
     * @return
     */
    Boolean deleteComment(Long id);

    /**
     * 获取评论
     *
     * @param pageRequest 分页查询请求体
     * @return
     */
    Page<Comment> getCommentPage(CommentPageRequest pageRequest);

    /**
     * 获取评论
     *
     * @param queryRequest
     * @return
     */
    Page<CommentTreeVO> getCommentTreeVOPage(CommentQueryRequest queryRequest);
}
