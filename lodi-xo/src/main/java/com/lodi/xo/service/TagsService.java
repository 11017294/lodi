package com.lodi.xo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.service.BaseService;
import com.lodi.common.model.entity.Tags;
import com.lodi.common.model.request.tags.TagsAddRequest;
import com.lodi.common.model.request.tags.TagsPageRequest;
import com.lodi.common.model.request.tags.TagsUpdateRequest;

import java.util.List;

/**
 * 标签 服务层
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
public interface TagsService extends BaseService<Tags> {

    /**
     * 新增标签
     *
     * @param addRequest 新增请求体
     * @return
     */
    Boolean insertTags(TagsAddRequest addRequest);

    /**
     * 更新标签
     *
     * @param updateRequest 更新请求体
     * @return
     */
    Boolean updateTags(TagsUpdateRequest updateRequest);

    /**
     * 删除标签
     *
     * @param id 标签ID
     * @return
     */
    Boolean deleteTags(Long id);

    /**
     * 获取标签
     *
     * @param pageRequest 分页查询请求体
     * @return
     */
    Page<Tags> getTagsPage(TagsPageRequest pageRequest);

    /**
     * 获取最热标签
     *
     * @return
     */
    List<Tags> getHotTag();

}
