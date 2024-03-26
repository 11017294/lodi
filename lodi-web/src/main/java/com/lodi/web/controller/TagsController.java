package com.lodi.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.domain.Result;
import com.lodi.common.model.convert.tags.TagsConvert;
import com.lodi.common.model.entity.Tags;
import com.lodi.common.model.vo.ArticleVO;
import com.lodi.common.model.vo.TagsVO;
import com.lodi.xo.service.ArticleService;
import com.lodi.xo.service.TagsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MaybeBin
 * @createDate 2023-11-30
 */
@Slf4j
@Tag(name = "标签相关接口")
@RestController
@RequestMapping("tags")
public class TagsController {

    @Resource
    private ArticleService articleService;
    @Resource
    private TagsService tagsService;

    @Operation(summary = "获取标签信息")
    @GetMapping("getTagList")
    public Result<List<TagsVO>> getTagList() {
        log.info("获取标签信息");
        List<Tags> tagsList = tagsService.list();
        return Result.success(TagsConvert.INSTANCE.toVO(tagsList));
    }

    @Operation(summary = "根据标签获取文章列表")
    @GetMapping("getArticleByTagId")
    public Result<Page<ArticleVO>> getArticleByTagId(@RequestParam("currentPage") Long currentPage, @RequestParam("tagId") Long tagId) {
        log.info("根据标签获取文章列表");
        Page<ArticleVO> articlePage = articleService.getArticleByTagId(currentPage, tagId);
        return Result.success(articlePage);
    }

}
