package com.lodi.admin.controller;

import com.lodi.common.model.entity.Category;
import com.lodi.common.model.entity.Tags;
import com.lodi.xo.service.TagsService;
import com.lodi.common.model.request.tags.TagsAddRequest;
import com.lodi.common.model.request.tags.TagsUpdateRequest;
import com.lodi.common.model.request.tags.TagsPageRequest;
import com.lodi.common.model.vo.TagsVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.web.domain.Result;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.request.IdRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import static com.lodi.common.core.enums.ErrorCode.NOT_FOUND_ERROR;

/**
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Tag(name = "标签", description = "标签")
@RestController
@RequestMapping("/tags")
public class TagsController {

    @Resource
    private TagsService tagsService;

    @Operation(summary = "获取标签分页")
    @GetMapping("page")
    public Result<Page<Tags>> getTagsPage(@ParameterObject TagsPageRequest pageRequest) {
        return Result.success(tagsService.getTagsPage(pageRequest));
    }

    @Operation(summary = "获取标签列表")
    @GetMapping("/list")
    public Result<List<Tags>> getTagsList() {
        return Result.success(tagsService.list());
    }

    @Operation(summary = "获取标签")
    @GetMapping("get")
    public Result<TagsVO> getTags(@ParameterObject IdRequest request) {
        Tags tags = tagsService.getById(request.getId());
        if (tags == null) {
            throw new BusinessException(NOT_FOUND_ERROR);
        }
        TagsVO tagsVO = new TagsVO();
        BeanUtils.copyProperties(tags, tagsVO);
        return Result.success(tagsVO);
    }

    @Operation(summary = "新增标签")
    @PostMapping("add")
    public Result<Boolean> addTags(@RequestBody TagsAddRequest addRequest) {
        return Result.success(tagsService.insertTags(addRequest));
    }

    @Operation(summary = "更新标签")
    @PutMapping("update")
    public Result<Boolean> updateTags(@RequestBody TagsUpdateRequest updateRequest) {
        return Result.success(tagsService.updateTags(updateRequest));
    }

    @Operation(summary = "删除标签")
    @DeleteMapping("delete")
    public Result<Boolean> deleteTags(@RequestBody IdRequest request) {
        return Result.success(tagsService.deleteTags(request.getId()));
    }
}
