package com.lodi.admin.controller;

import com.lodi.common.core.constant.RedisKeyConstants;
import com.lodi.common.core.domain.Result;
import com.lodi.xo.service.ArticleService;
import com.lodi.xo.service.CommentService;
import com.lodi.xo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    private final UserService userService;
    private final ArticleService articleService;
    private final CommentService commentService;

    @Operation(summary = "文章数,用户数,评论数,IP数")
    @GetMapping("count")
    @Cacheable(cacheNames = RedisKeyConstants.DATA_COUNT)
    public Result<Map<String, Long>> count() {
        log.info("获取文章数,用户数,评论数,IP数");
        Map<String, Long> map = new HashMap<>();
        map.put("userCount", userService.count());
        map.put("commentCount", commentService.count());
        map.put("articleCount", articleService.getArticleCount());
        // todo 后续增加浏览量
        return Result.success(map);
    }

    @Operation(summary = "文章贡献度")
    @GetMapping("getArticleContributeCount")
    @Cacheable(cacheNames = RedisKeyConstants.ARTICLE_CONTRIBUTE_COUNT)
    public Result getArticleContributeCount() {
        log.info("获取文章贡献度");
        return Result.success(articleService.getArticleContributeCount());
    }

}
