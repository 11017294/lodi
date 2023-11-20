package com.lodi.common.model.request.animeInfo;

import com.lodi.common.core.web.page.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 动漫信息分页查询 请求体
 *
 * @author MaybeBin
 * @createDate 2023-11-20
 */
@Data
@Schema(title = "动漫信息分页查询 请求体")
public class AnimeInfoPageRequest extends PageRequest {

    private static final long serialVersionUID = 1L;

    @Schema(title = "番名", description = "番名")
    private String animeName;

    @Schema(title = "地区", description = "地区")
    private String region;

    @Schema(title = "动漫种类（TV、剧场）", description = "动漫种类（TV、剧场）")
    private String animeType;

    @Schema(title = "季度编号", description = "季度编号")
    private String seasonNumber;

    @Schema(title = "动漫类型", description = "动漫类型")
    private String animeGenre;

    @Schema(title = "作者", description = "作者")
    private String author;

    @Schema(title = "制作公司", description = "制作公司")
    private String productionCompany;

    @Schema(title = "配音演员列表（LIST）", description = "配音演员列表（LIST）")
    private String voiceActors;

    @Schema(title = "剧情介绍", description = "剧情介绍")
    private String synopsis;

    @Schema(title = "播放状态：0-未放映、1-正在更新、2-已完结", description = "播放状态：0-未放映、1-正在更新、2-已完结")
    private Integer airingStatus;

}
