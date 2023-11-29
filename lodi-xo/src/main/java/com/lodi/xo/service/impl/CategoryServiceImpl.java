package com.lodi.xo.service.impl;

import com.lodi.common.model.entity.Category;
import com.lodi.xo.mapper.CategoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import com.lodi.common.model.request.category.CategoryAddRequest;
import com.lodi.common.model.request.category.CategoryUpdateRequest;
import com.lodi.common.model.request.category.CategoryPageRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.xo.service.CategoryService;
import com.lodi.common.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文章类别 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public Boolean insertCategory(CategoryAddRequest addRequest) {
        Category category = new Category();
        BeanUtils.copyProperties(addRequest, category);
        return save(category);
    }

    @Override
    public Boolean updateCategory(CategoryUpdateRequest updateRequest) {
        Category category = new Category();
        BeanUtils.copyProperties(updateRequest, category);
        return updateById(category);
    }

    @Override
    public Boolean deleteCategory(Long id) {
        return removeById(id);
    }

    @Override
    public Page<Category> getCategoryPage(CategoryPageRequest pageRequest) {
        Page<Category> page = new Page<>(pageRequest.getCurrent(), pageRequest.getPageSize());
        LambdaQueryWrapper<Category> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<Category> buildQueryWrapper(CategoryPageRequest pageRequest) {
        return new LambdaQueryWrapper<Category>();
    }
}
