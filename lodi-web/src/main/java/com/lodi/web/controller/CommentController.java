package com.lodi.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.domain.Result;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.comment.CommentAddRequest;
import com.lodi.common.model.request.comment.CommentQueryRequest;
import com.lodi.common.model.vo.CommentTreeVO;
import com.lodi.xo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author MaybeBin
 * @createDate 2024-04-08
 */
@Tag(name = "评论", description = "评论")
@RestController
@RequestMapping("comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Operation(summary = "获取评论分页")
    @GetMapping("getCommentTree")
    public Result<Page<CommentTreeVO>> getCommentTree(@Validated CommentQueryRequest queryRequest) {
        Page<CommentTreeVO> commentTreeVOPage = commentService.getCommentTreeVOPage(queryRequest);
        return Result.success(commentTreeVOPage);
    }

    @Operation(summary = "新增评论")
    @PostMapping("add")
    public Result<Boolean> addComment(@RequestBody @Validated CommentAddRequest addRequest) {
        return Result.success(commentService.insertComment(addRequest));
    }

    @Operation(summary = "删除评论")
    @DeleteMapping("delete")
    public Result<Boolean> deleteComment(@RequestBody @Validated IdRequest request) {
        return Result.success(commentService.deleteComment(request.getId()));
    }
}
