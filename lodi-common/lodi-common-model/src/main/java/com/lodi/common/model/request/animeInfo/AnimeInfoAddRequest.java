package com.lodi.common.model.request.animeInfo;

import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加动漫信息 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-20
 */
@Data
@Schema(description = "添加动漫信息 请求体")
public class AnimeInfoAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "番名")
    @NotBlank(message = "番名不能为空")
    private String animeName;

    @Schema(description = "地区")
    private String region;

    @Schema(description = "动漫种类（TV、剧场）")
    @NotBlank(message = "动漫种类不能为空")
    private String animeType;

    @Schema(description = "季度编号")
    @NotBlank(message = "季度编号不能为空")
    private String seasonNumber;

    @Schema(description = "动漫类型")
    @NotBlank(message = "动漫类型不能为空")
    private String animeGenre;

    @Schema(description = "首播时间")
    @NotBlank(message = "首播时间不能为空")
    private LocalDate firstAirDate;

    @Schema(description = "播放地址（LIST）")
    private String playUrls;

    @Schema(description = "名场面图片（LIST）")
    private String highlightImages;

    @Schema(description = "封面地址")
    private String coverUrl;

    @Schema(description = "作者")
    @NotBlank(message = "作者不能为空")
    private String author;

    @Schema(description = "制作公司")
    @NotBlank(message = "制作公司不能为空")
    private String productionCompany;

    @Schema(description = "配音演员列表（LIST）")
    private String voiceActors;

    @Schema(description = "剧情介绍")
    private String synopsis;

    @Schema(description = "播放状态：0-未放映、1-正在更新、2-已完结")
    @NotNull(message = "播放状态不能为空")
    private Integer airingStatus;

    @Schema(description = "总集数")
    @NotNull(message = "总集数不能为空")
    private Integer totalEpisodes;

    @Schema(description = "当前集数")
    @NotNull(message = "当前集数不能为空")
    private Integer currentEpisode;

}
