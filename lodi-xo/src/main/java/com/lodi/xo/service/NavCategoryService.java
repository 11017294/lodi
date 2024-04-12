package com.lodi.xo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.NavCategory;
import com.lodi.common.model.request.navCategory.NavCategoryAddRequest;
import com.lodi.common.model.request.navCategory.NavCategoryPageRequest;
import com.lodi.common.model.request.navCategory.NavCategoryUpdateRequest;
import com.lodi.common.mybatis.service.BaseService;

/**
 * 导航分类表 服务层
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
public interface NavCategoryService extends BaseService<NavCategory> {

    /**
     * 新增导航分类表
     *
     * @param addRequest 新增请求体
     * @return
     */
    Boolean insertNavCategory(NavCategoryAddRequest addRequest);

    /**
     * 更新导航分类表
     *
     * @param updateRequest 更新请求体
     * @return
     */
    Boolean updateNavCategory(NavCategoryUpdateRequest updateRequest);

    /**
     * 删除导航分类表
     *
     * @param id 导航分类表ID
     * @return
     */
    Boolean deleteNavCategory(Long id);

    /**
     * 获取导航分类表
     *
     * @param pageRequest 分页查询请求体
     * @return
     */
    Page<NavCategory> getNavCategoryPage(NavCategoryPageRequest pageRequest);

}
