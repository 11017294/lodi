package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.convert.navigate.NavigateConvert;
import com.lodi.common.model.entity.NavCategory;
import com.lodi.common.model.entity.Navigate;
import com.lodi.common.model.request.navigate.NavigateAddRequest;
import com.lodi.common.model.request.navigate.NavigatePageRequest;
import com.lodi.common.model.request.navigate.NavigateQueryRequest;
import com.lodi.common.model.request.navigate.NavigateUpdateRequest;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import com.lodi.xo.mapper.NavigateMapper;
import com.lodi.xo.service.NavigateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.lodi.common.core.constant.CommonConstant.FRIEND_LINK;

/**
 * 导航 服务层实现
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
@Service
@Slf4j
@AllArgsConstructor
public class NavigateServiceImpl extends BaseServiceImpl<NavigateMapper, Navigate> implements NavigateService {

    private final NavigateServiceCoordinator navigateServiceCoordinator;

    @Override
    public Boolean insertNavigate(NavigateAddRequest addRequest) {
        Navigate navigate = NavigateConvert.INSTANCE.toEntity(addRequest);
        return save(navigate);
    }

    @Override
    public Boolean updateNavigate(NavigateUpdateRequest updateRequest) {
        Navigate navigate = NavigateConvert.INSTANCE.toEntity(updateRequest);
        return updateById(navigate);
    }

    @Override
    public Boolean deleteNavigate(Long id) {
        return removeById(id);
    }

    @Override
    public Page<Navigate> getNavigatePage(NavigatePageRequest pageRequest) {
        Page<Navigate> page = new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize());
        LambdaQueryWrapper<Navigate> queryWrapper = buildQueryWrapper(pageRequest);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Navigate> getList(NavigateQueryRequest queryRequest) {
        LambdaQueryWrapper<Navigate> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(queryRequest.getNavCategoryId()),
                        Navigate::getNavCategoryId, queryRequest.getNavCategoryId())
                .and(StringUtils.isNotBlank(queryRequest.getKeyword()), i -> {
                    i.like(Navigate::getTitle, queryRequest.getKeyword()).or()
                            .like(Navigate::getContent, queryRequest.getKeyword()).or()
                            .like(Navigate::getSummary, queryRequest.getKeyword());
                });

        wrapper.orderByAsc(Navigate::getSort);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Navigate> getFriendLinkList() {
        NavCategory navCategory = navigateServiceCoordinator.getCategoryByName(FRIEND_LINK);

        LambdaQueryWrapper<Navigate> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Navigate::getNavCategoryId, navCategory.getId());

        wrapper.orderByAsc(Navigate::getSort);
        return baseMapper.selectList(wrapper);
    }

    private LambdaQueryWrapper<Navigate> buildQueryWrapper(NavigatePageRequest pageRequest) {
        return new LambdaQueryWrapper<Navigate>();
    }
}
