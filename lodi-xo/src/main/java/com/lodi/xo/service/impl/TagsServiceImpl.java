package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.service.impl.BaseServiceImpl;
import com.lodi.common.model.convert.tags.TagsConvert;
import com.lodi.common.model.entity.Tags;
import com.lodi.common.model.request.tags.TagsAddRequest;
import com.lodi.common.model.request.tags.TagsPageRequest;
import com.lodi.common.model.request.tags.TagsUpdateRequest;
import com.lodi.xo.mapper.TagsMapper;
import com.lodi.xo.service.TagsService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lodi.common.core.constant.ArticleConstant.PAGE_SIZE;

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
        Tags tags = TagsConvert.INSTANCE.toEntity(addRequest);
        return save(tags);
    }

    @Override
    public Boolean updateTags(TagsUpdateRequest updateRequest) {
        Tags tags = TagsConvert.INSTANCE.toEntity(updateRequest);
        return updateById(tags);
    }

    @Override
    public Boolean deleteTags(Long id) {
        return removeById(id);
    }

    @Override
    public Page<Tags> getTagsPage(TagsPageRequest pageRequest) {
        Page<Tags> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<Tags> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    private LambdaQueryWrapper<Tags> buildQueryWrapper(TagsPageRequest pageRequest) {
        return new LambdaQueryWrapper<Tags>();
    }

    @Override
    public List<Tags> getHotTag() {
        LambdaQueryWrapper<Tags> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Tags::getClickCount);
        IPage<Tags> tagsIPage = baseMapper.selectPage(new Page(1, PAGE_SIZE), queryWrapper);
        return tagsIPage.getRecords();
    }
}
