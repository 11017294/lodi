package com.lodi.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.domain.Result;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.convert.comment.CommentConvert;
import com.lodi.common.model.entity.Comment;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.comment.CommentAddRequest;
import com.lodi.common.model.request.comment.CommentPageRequest;
import com.lodi.common.model.request.comment.CommentUpdateRequest;
import com.lodi.common.model.vo.CommentVO;
import com.lodi.xo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import static com.lodi.common.core.enums.ErrorCode.NOT_FOUND_ERROR;

/**
 * @author MaybeBin
 * @createDate 2024-04-03
 */
@Tag(name = "评论", description = "评论")
@RestController
@AllArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "获取评论分页")
    @GetMapping("page")
    public Result<Page<CommentVO>> getCommentPage(@ParameterObject CommentPageRequest pageRequest) {
        Page<Comment> commentPage = commentService.getCommentPage(pageRequest);
        return Result.success(CommentConvert.INSTANCE.toVO(commentPage));
    }

    @Operation(summary = "获取评论")
    @GetMapping("get")
    public Result<CommentVO> getComment(@ParameterObject IdRequest request) {
        Comment comment = commentService.getById(request.getId());
        if (comment == null) {
            throw new BusinessException(NOT_FOUND_ERROR);
        }
        return Result.success(CommentConvert.INSTANCE.toVO(comment));
    }

    @Operation(summary = "新增评论")
    @PostMapping("add")
    public Result<Boolean> addComment(@RequestBody CommentAddRequest addRequest) {
        return Result.success(commentService.insertComment(addRequest));
    }

    @Operation(summary = "更新评论")
    @PutMapping("update")
    public Result<Boolean> updateComment(@RequestBody CommentUpdateRequest updateRequest) {
        return Result.success(commentService.updateComment(updateRequest));
    }

    @Operation(summary = "删除评论")
    @DeleteMapping("delete")
    public Result<Boolean> deleteComment(@RequestBody IdRequest request) {
        return Result.success(commentService.deleteComment(request.getId()));
    }
}
