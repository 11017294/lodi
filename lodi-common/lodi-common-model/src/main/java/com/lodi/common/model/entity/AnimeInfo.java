package com.lodi.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lodi.common.core.web.domain.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 动漫信息
 *
 * @author MaybeBin
 * @createDate 2023-11-20
 */
@Data
@Accessors(chain = true)
@TableName("t_anime_info")
public class AnimeInfo extends BaseEntity<AnimeInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 系列ID
    */
    private Long seriesId;

    /**
    * 番名
    */
    private String animeName;

    /**
    * 地区
    */
    private String region;

    /**
    * 动漫种类（TV、剧场）
    */
    private String animeType;

    /**
    * 季度编号
    */
    private String seasonNumber;

    /**
    * 动漫类型
    */
    private String animeGenre;

    /**
    * 首播时间
    */
    private LocalDate firstAirDate;

    /**
    * 播放地址（LIST）
    */
    private String playUrls;

    /**
    * 名场面图片（LIST）
    */
    private String highlightImages;

    /**
    * 封面地址
    */
    private String coverUrl;

    /**
    * 作者
    */
    private String author;

    /**
    * 制作公司
    */
    private String productionCompany;

    /**
    * 配音演员列表（LIST）
    */
    private String voiceActors;

    /**
    * 剧情介绍
    */
    private String synopsis;

    /**
    * 播放状态：0-未放映、1-正在更新、2-已完结
    */
    private Integer airingStatus;

    /**
    * 总集数
    */
    private Integer totalEpisodes;

    /**
    * 当前集数
    */
    private Integer currentEpisode;

    /**
    * 追番人数
    */
    private Integer followersCount;

    /**
    * 评分
    */
    private BigDecimal rating;
}
