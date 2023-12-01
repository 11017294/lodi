package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.service.impl.BaseServiceImpl;
import com.lodi.common.model.convert.category.CategoryConvert;
import com.lodi.common.model.entity.Category;
import com.lodi.common.model.request.category.CategoryAddRequest;
import com.lodi.common.model.request.category.CategoryPageRequest;
import com.lodi.common.model.request.category.CategoryUpdateRequest;
import com.lodi.xo.mapper.CategoryMapper;
import com.lodi.xo.service.CategoryService;
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
        Category category = CategoryConvert.INSTANCE.toEntity(addRequest);
        return save(category);
    }

    @Override
    public Boolean updateCategory(CategoryUpdateRequest updateRequest) {
        Category category = CategoryConvert.INSTANCE.toEntity(updateRequest);
        return updateById(category);
    }

    @Override
    public Boolean deleteCategory(Long id) {
        return removeById(id);
    }

    @Override
    public Page<Category> getCategoryPage(CategoryPageRequest pageRequest) {
        Page<Category> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<Category> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<Category> buildQueryWrapper(CategoryPageRequest pageRequest) {
        return new LambdaQueryWrapper<Category>();
    }

    @Override
    public void incrementClickCount(Long id) {
        // todo 后续增加ip限制，每个ip每天只计算一次点击次数
        LambdaUpdateWrapper<Category> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.setSql(" click_count = click_count + 1 ")
                .eq(Category::getId, id);
        baseMapper.update(updateWrapper);
    }
}
