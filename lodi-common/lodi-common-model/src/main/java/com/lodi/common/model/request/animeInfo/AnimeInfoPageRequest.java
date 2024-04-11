package com.lodi.common.model.request.animeInfo;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.lodi.common.mybatis.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 动漫信息分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Data
@Schema(description = "动漫信息分页查询 请求体")
public class AnimeInfoPageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Schema(description = "系列ID")
    private Long seriesId;

    @Schema(description = "番名")
    private String animeName;

    @Schema(description = "地区")
    private String region;

    @Schema(description = "动漫种类（TV、剧场）")
    private String animeType;

    @Schema(description = "季度编号")
    private String seasonNumber;

    @Schema(description = "动漫类型")
    private String animeGenre;

    @Schema(description = "首播时间")
    private LocalDate firstAirDate;

    @Schema(description = "播放地址（LIST）")
    private String playUrls;

    @Schema(description = "名场面图片（LIST）")
    private String highlightImages;

    @Schema(description = "封面地址")
    private String coverUrl;

    @Schema(description = "作者")
    private String author;

    @Schema(description = "制作公司")
    private String productionCompany;

    @Schema(description = "配音演员列表（LIST）")
    private String voiceActors;

    @Schema(description = "剧情介绍")
    private String synopsis;

    @Schema(description = "播放状态：0-未放映、1-正在更新、2-已完结")
    private Integer airingStatus;

    @Schema(description = "总集数")
    private Integer totalEpisodes;

    @Schema(description = "当前集数")
    private Integer currentEpisode;

    @Schema(description = "追番人数")
    private Integer followersCount;

    @Schema(description = "评分")
    private BigDecimal rating;
}
