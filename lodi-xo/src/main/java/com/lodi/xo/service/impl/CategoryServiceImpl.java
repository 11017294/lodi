package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.constant.StatusConstant;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.vo.CategoryVO;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import com.lodi.common.model.convert.category.CategoryConvert;
import com.lodi.common.model.entity.Category;
import com.lodi.common.model.request.category.CategoryAddRequest;
import com.lodi.common.model.request.category.CategoryPageRequest;
import com.lodi.common.model.request.category.CategoryUpdateRequest;
import com.lodi.xo.mapper.ArticleMapper;
import com.lodi.xo.mapper.CategoryMapper;
import com.lodi.xo.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章类别 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Slf4j
@Service
@AllArgsConstructor
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category> implements CategoryService {

    private final ArticleMapper articleMapper;

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

    @Override
    public void validateCategoryId(Long categoryId) {
        if (categoryId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getId, categoryId);

        long count = count(queryWrapper);
        if(count == 0){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "文章类别不存在");
        }
    }

    @Override
    public List<CategoryVO> getCategoryArticleCount() {
        return list().stream().map(category -> {
            // 查询文章数
            LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Article::getCategoryId, category.getId());
            queryWrapper.eq(Article::getIsPublish, StatusConstant.ON);
            queryWrapper.eq(Article::getAuditStatus, StatusConstant.ON);

            Long articleCount = articleMapper.selectCount(queryWrapper);
            // 设置文章数
            CategoryVO vo = CategoryConvert.INSTANCE.toVO(category);
            vo.setArticleCount(articleCount);
            return vo;
        }).collect(Collectors.toList());
    }
}
