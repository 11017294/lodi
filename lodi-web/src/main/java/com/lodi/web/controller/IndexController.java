package com.lodi.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.constant.ArticleConstant;
import com.lodi.common.core.domain.Result;
import com.lodi.common.model.convert.navigate.NavigateConvert;
import com.lodi.common.model.convert.tags.TagsConvert;
import com.lodi.common.model.entity.Navigate;
import com.lodi.common.model.entity.Tags;
import com.lodi.common.model.request.IdRequest;
import com.lodi.common.model.system.WebsiteBasic;
import com.lodi.common.model.vo.ArticleVO;
import com.lodi.common.model.vo.NavigateVO;
import com.lodi.common.model.vo.TagsVO;
import com.lodi.common.mybatis.page.PageRequest;
import com.lodi.xo.service.ArticleService;
import com.lodi.xo.service.CategoryService;
import com.lodi.xo.service.NavigateService;
import com.lodi.xo.service.TagsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MaybeBin
 * @createDate 2023-11-29
 */
@Slf4j
@Tag(name = "首页相关接口")
@RestController
@AllArgsConstructor
@RequestMapping("index")
public class IndexController {

    private final ArticleService articleService;
    private final TagsService tagsService;
    private final CategoryService categoryService;
    private final NavigateService navigateService;

    @Operation(summary = "获取文章信息")
    @GetMapping("get")
    public Result<ArticleVO> getArticle(@ParameterObject @Validated IdRequest idRequest) {
        log.info("获取文章信息");
        ArticleVO articleVO = articleService.getArticleById(idRequest.getId());
        return Result.success(articleVO);
    }

    @Operation(summary = "首页搜索文章")
    @GetMapping("getArticleBySearch")
    public Result<Page<ArticleVO>> getArticleBySearch(@RequestParam("currentPage") Long currentPage, @RequestParam("keywords") String keyword) {
        log.info("首页搜索文章");
        Page<ArticleVO> articlePage = articleService.getArticleBySearch(currentPage, keyword);
        return Result.success(articlePage);
    }

    @Operation(summary = "获取首页最新的文章")
    @GetMapping("getNewArticle")
    public Result<Page<ArticleVO>> getNewArticle(@RequestParam("currentPage") Long currentPage) {
        log.info("获取首页最新的文章");
        Page<ArticleVO> articles = articleService.getArticleByOrderDesc(currentPage, ArticleConstant.CREATE_TIME);
        return Result.success(articles);
    }

    @Operation(summary = "获取首页最热门的文章")
    @GetMapping("getHotArticle")
    public Result<Page<ArticleVO>> getHotArticle(@RequestParam("currentPage") Long currentPage) {
        log.info("获取首页最热门的文章");
        Page<ArticleVO> articles = articleService.getArticleByOrderDesc(currentPage, ArticleConstant.CLICK_COUNT);
        return Result.success(articles);
    }

    @Operation(summary = "获取首页推荐的文章")
    @GetMapping("getRecommendedArticle")
    public Result<Page<ArticleVO>> getRecommendedArticle(@RequestParam("currentPage") Long currentPage) {
        log.info("获取首页推荐的文章");
        Page<ArticleVO> articles = articleService.getArticleByOrderDesc(currentPage, ArticleConstant.SORT);
        return Result.success(articles);
    }

    @Operation(summary = "获取最热标签")
    @GetMapping("getHotTag")
    public Result<List<TagsVO>> getHotTag() {
        log.info("获取最热标签");
        List<Tags> tagsList = tagsService.getHotTag();
        return Result.success(TagsConvert.INSTANCE.toVO(tagsList));
    }

    @Operation(summary = "友链信息展示")
    @GetMapping("/friendLink")
    public Result<List<NavigateVO>> friendLink() {
        List<Navigate> friendLinkList = navigateService.getFriendLinkList();
        return Result.success(NavigateConvert.INSTANCE.toVO(friendLinkList));
    }

    @Operation(summary = "获取站点信息")
    @GetMapping("findShowBasic")
    public Result<WebsiteBasic> findShowBasic() {
        log.info("获取站点信息");
        long articleCount = articleService.getArticleCount();
        long tagCount = tagsService.count();
        long categoryCount = categoryService.count();
        WebsiteBasic websiteBasic = WebsiteBasic.builder()
                .articleCount(articleCount)
                .tagCount(tagCount)
                .categoryCount(categoryCount)
                .build();
        return Result.success(websiteBasic);
    }
}
