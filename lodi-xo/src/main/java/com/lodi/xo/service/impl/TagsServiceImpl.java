package com.lodi.xo.service.impl;

import com.lodi.common.model.entity.Tags;
import com.lodi.xo.mapper.TagsMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import com.lodi.common.model.request.tags.TagsAddRequest;
import com.lodi.common.model.request.tags.TagsUpdateRequest;
import com.lodi.common.model.request.tags.TagsPageRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.xo.service.TagsService;
import com.lodi.common.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 标签 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Service
public class TagsServiceImpl extends BaseServiceImpl<TagsMapper, Tags> implements TagsService {

    @Override
    public Boolean insertTags(TagsAddRequest addRequest) {
        Tags tags = new Tags();
        BeanUtils.copyProperties(addRequest, tags);
        return updateById(tags);
    }

    @Override
    public Boolean updateTags(TagsUpdateRequest updateRequest) {
        Tags tags = new Tags();
        BeanUtils.copyProperties(updateRequest, tags);
        return updateById(tags);
    }

    @Override
    public Boolean deleteTags(Long id) {
        return removeById(id);
    }

    @Override
    public Page<Tags> getTagsPage(TagsPageRequest pageRequest) {
        Page<Tags> page = new Page<>(pageRequest.getCurrent(), pageRequest.getPageSize());
        LambdaQueryWrapper<Tags> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<Tags> buildQueryWrapper(TagsPageRequest pageRequest) {
        return new LambdaQueryWrapper<Tags>();
    }
}
