package com.lodi.xo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.Navigate;
import com.lodi.common.model.request.navigate.NavigateAddRequest;
import com.lodi.common.model.request.navigate.NavigatePageRequest;
import com.lodi.common.model.request.navigate.NavigateQueryRequest;
import com.lodi.common.model.request.navigate.NavigateUpdateRequest;
import com.lodi.common.model.vo.NavigateVO;
import com.lodi.common.mybatis.service.BaseService;

import java.util.List;

/**
 * 导航 服务层
 *
 * @author MaybeBin
 * @createDate 2024-04-11
 */
public interface NavigateService extends BaseService<Navigate> {

    /**
     * 新增导航
     *
     * @param addRequest 新增请求体
     * @return
     */
    Boolean insertNavigate(NavigateAddRequest addRequest);

    /**
     * 更新导航
     *
     * @param updateRequest 更新请求体
     * @return
     */
    Boolean updateNavigate(NavigateUpdateRequest updateRequest);

    /**
     * 删除导航
     *
     * @param id 导航ID
     * @return
     */
    Boolean deleteNavigate(Long id);

    /**
     * 获取导航
     *
     * @param pageRequest 分页查询请求体
     * @return
     */
    Page<Navigate> getNavigatePage(NavigatePageRequest pageRequest);

    /**
     * 获取导航列表
     *
     * @param queryRequest
     * @return
     */
    List<Navigate> getList(NavigateQueryRequest queryRequest);
}
