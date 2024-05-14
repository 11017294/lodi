package com.lodi.common.core.constant;

/**
 * Redis Key 枚举类
 *
 * @author MaybeBin
 * @createDate 2024-05-13
 */
public interface RedisKeyConstants {

    /**
     * 首页推荐文章
     */
    String RECOMMENDED_ARTICLE = "recommended_article#1m";

    /**
     * 首页最热门文章
     */
    String HOT_ARTICLE = "hot_article#5m";

    /**
     * 最热门标签
     */
    String HOT_TAG = "hot_tag#30m";

    /**
     * 友链
     */
    String FRIEND_LINK = "friend_link#1h";

    /**
     * 站点信息
     */
    String SHOW_BASIC = "show_basic#1d";

    /**
     * 分类列表
     */
    String CATEGORY_LIST = "category_list#2h";

    /**
     * 标签列表
     */
    String TAG_LIST = "tag_list#2h";

    /**
     * 导航分类列表
     */
    String NAV_CATEGORY_LIST = "nav_category_list#2h";

}
