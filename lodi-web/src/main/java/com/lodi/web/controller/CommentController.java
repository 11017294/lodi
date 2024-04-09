package com.lodi.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.domain.Result;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.convert.comment.CommentConvert;
import com.lodi.common.model.entity.Comment;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.request.comment.CommentAddRequest;
import com.lodi.common.model.request.comment.CommentPageRequest;
import com.lodi.common.model.request.comment.CommentUpdateRequest;
import com.lodi.common.model.vo.CommentTreeVO;
import com.lodi.common.model.vo.CommentVO;
import com.lodi.xo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.lodi.common.core.enums.ErrorCode.NOT_FOUND_ERROR;

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
    @GetMapping("getByArticleId")
    public Result<Page<CommentTreeVO>> getByArticleId(@RequestParam("articleId") Long articleId,
                                                      @RequestParam("currentPage") Long currentPage,
                                                      @RequestParam("pageSize") Long pageSize) {
        Page<CommentTreeVO> commentTreeVOPage = commentService.getByArticleId(articleId, currentPage, pageSize);
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
