package com.lodi.xo.service.impl;

import com.lodi.common.model.entity.NavCategory;
import com.lodi.xo.mapper.NavCategoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lodi.common.model.request.navCategory.NavCategoryAddRequest;
import com.lodi.common.model.request.navCategory.NavCategoryUpdateRequest;
import com.lodi.common.model.request.navCategory.NavCategoryPageRequest;
import com.lodi.common.model.convert.navCategory.NavCategoryConvert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.xo.service.NavCategoryService;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 导航分类表 服务层实现
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Service
public class NavCategoryServiceImpl extends BaseServiceImpl<NavCategoryMapper, NavCategory> implements NavCategoryService {

    @Override
    public Boolean insertNavCategory(NavCategoryAddRequest addRequest) {
        NavCategory navCategory = NavCategoryConvert.INSTANCE.toEntity(addRequest);
        return save(navCategory);
    }

    @Override
    public Boolean updateNavCategory(NavCategoryUpdateRequest updateRequest) {
        NavCategory navCategory = NavCategoryConvert.INSTANCE.toEntity(updateRequest);
        return updateById(navCategory);
    }

    @Override
    public Boolean deleteNavCategory(Long id) {
        return removeById(id);
    }

    @Override
    public Page<NavCategory> getNavCategoryPage(NavCategoryPageRequest pageRequest) {
        Page<NavCategory> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<NavCategory> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public NavCategory getCategoryByName(String name) {
        LambdaQueryWrapper<NavCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NavCategory::getName, name);
        return baseMapper.selectOne(wrapper);
    }

    private LambdaQueryWrapper<NavCategory> buildQueryWrapper(NavCategoryPageRequest pageRequest) {
        return new LambdaQueryWrapper<NavCategory>();
    }
}
