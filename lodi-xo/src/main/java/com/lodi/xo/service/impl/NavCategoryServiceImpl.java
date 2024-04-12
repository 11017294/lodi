package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.convert.navCategory.NavCategoryConvert;
import com.lodi.common.model.entity.NavCategory;
import com.lodi.common.model.request.navCategory.NavCategoryAddRequest;
import com.lodi.common.model.request.navCategory.NavCategoryPageRequest;
import com.lodi.common.model.request.navCategory.NavCategoryUpdateRequest;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import com.lodi.xo.mapper.NavCategoryMapper;
import com.lodi.xo.service.NavCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 导航分类表 服务层实现
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Service
@Slf4j
@AllArgsConstructor
public class NavCategoryServiceImpl extends BaseServiceImpl<NavCategoryMapper, NavCategory> implements NavCategoryService {

    private final NavigateServiceCoordinator navigateServiceCoordinator;

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
    @Transactional
    public Boolean deleteNavCategory(Long id) {
        Boolean flag = removeById(id);
        navigateServiceCoordinator.deleteNavigateByCategoryId(id);
        log.info("删除导航分类成功");
        return flag;
    }

    @Override
    public Page<NavCategory> getNavCategoryPage(NavCategoryPageRequest pageRequest) {
        Page<NavCategory> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<NavCategory> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<NavCategory> buildQueryWrapper(NavCategoryPageRequest pageRequest) {
        return new LambdaQueryWrapper<NavCategory>();
    }
}
