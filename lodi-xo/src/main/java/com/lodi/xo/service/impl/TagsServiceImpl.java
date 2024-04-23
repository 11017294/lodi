package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.constant.StatusConstant;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.convert.tags.TagsConvert;
import com.lodi.common.model.entity.Article;
import com.lodi.common.model.entity.Tags;
import com.lodi.common.model.request.tags.TagsAddRequest;
import com.lodi.common.model.request.tags.TagsPageRequest;
import com.lodi.common.model.request.tags.TagsUpdateRequest;
import com.lodi.common.model.vo.TagsVO;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import com.lodi.xo.mapper.ArticleMapper;
import com.lodi.xo.mapper.TagsMapper;
import com.lodi.xo.service.TagsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.lodi.common.core.constant.ArticleConstant.PAGE_SIZE;

/**
 * 标签 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Service
@AllArgsConstructor
public class TagsServiceImpl extends BaseServiceImpl<TagsMapper, Tags> implements TagsService {

    private final ArticleMapper articleMapper;

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

    @Override
    public void incrementClickCount(Long id) {
        // todo 后续增加ip限制，每个ip每天只计算一次点击次数
        LambdaUpdateWrapper<Tags> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.setSql(" click_count = click_count + 1 ")
                .eq(Tags::getId, id);
        baseMapper.update(updateWrapper);
    }

    @Override
    public void validateTagsId(List<Long> tags) {
        if (CollectionUtils.isEmpty(tags)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<Tags> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Tags::getId, tags);

        long count = count(queryWrapper);
        if (count != tags.size()) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "标签不存在");
        }
    }

    @Override
    public List<TagsVO> getTagsArticleCount() {
        return list().stream().map(tags -> {
            // 查询文章数
            LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(Article::getTagsId, tags.getId());
            queryWrapper.eq(Article::getIsPublish, StatusConstant.ON);
            queryWrapper.eq(Article::getAuditStatus, StatusConstant.ON);

            Long articleCount = articleMapper.selectCount(queryWrapper);
            // 设置文章数
            TagsVO vo = TagsConvert.INSTANCE.toVO(tags);
            vo.setArticleCount(articleCount);
            return vo;
        }).collect(Collectors.toList());
    }
}
