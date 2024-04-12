package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lodi.common.model.entity.NavCategory;
import com.lodi.common.model.entity.Navigate;
import com.lodi.xo.mapper.NavCategoryMapper;
import com.lodi.xo.mapper.NavigateMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 导航与导航分类的协调者（解决循环依赖）
 *
 * @author MaybeBin
 * @createDate 2024-04-12
 */
@Slf4j
@Service
@AllArgsConstructor
public class NavigateServiceCoordinator {

    private final NavigateMapper navigateMapper;
    private final NavCategoryMapper navCategoryMapper;

    /**
     * 根据分类id删除导航
     *
     * @param categoryId 分类id
     */
    public void deleteNavigateByCategoryId(Long categoryId) {
        LambdaQueryWrapper<Navigate> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.eq(Navigate::getNavCategoryId, categoryId);
        int deleteCount = navigateMapper.delete(deleteWrapper);
        log.info("删除了导航分类id:{}的导航{}条", categoryId, deleteCount);
    }

    /**
     * 根据分类名称获取导航分类
     *
     * @param categoryName 分类名称
     * @return
     */
    public NavCategory getCategoryByName(String categoryName) {
        LambdaQueryWrapper<NavCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NavCategory::getName, categoryName);
        return navCategoryMapper.selectOne(wrapper);
    }

}
