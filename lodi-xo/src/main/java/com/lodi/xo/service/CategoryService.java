package com.lodi.xo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.Category;
import com.lodi.common.model.request.category.CategoryAddRequest;
import com.lodi.common.model.request.category.CategoryPageRequest;
import com.lodi.common.model.request.category.CategoryUpdateRequest;
import com.lodi.common.model.vo.CategoryVO;
import com.lodi.common.mybatis.service.BaseService;

import java.util.List;

/**
 * 文章类别 服务层
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
public interface CategoryService extends BaseService<Category> {

    /**
     * 新增文章类别
     *
     * @param addRequest 新增请求体
     * @return
     */
    Boolean insertCategory(CategoryAddRequest addRequest);

    /**
     * 更新文章类别
     *
     * @param updateRequest 更新请求体
     * @return
     */
    Boolean updateCategory(CategoryUpdateRequest updateRequest);

    /**
     * 删除文章类别
     *
     * @param id 文章类别ID
     * @return
     */
    Boolean deleteCategory(Long id);

    /**
     * 获取文章类别
     *
     * @param pageRequest 分页查询请求体
     * @return
     */
    Page<Category> getCategoryPage(CategoryPageRequest pageRequest);

    /**
     * 增加点击次数
     *
     * @param id 分类id
     */
    void incrementClickCount(Long id);

    /**
     * 验证类别id
     *
     * @param categoryId
     */
    void validateCategoryId(Long categoryId);

    /**
     * 获取分类对应的文章数
     *
     * @return
     */
    List<CategoryVO> getCategoryArticleCount();
}
