package com.lodi.xo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.mybatis.service.BaseService;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.user.UserPageRequest;
import com.lodi.common.model.request.user.UserQueryRequest;
import com.lodi.common.model.request.user.UserSelfUpdateRequest;

import java.util.List;

/**
 * 用户 服务层
 *
 * @author MaybeBin
 * @createDate 2023-09-20
 */
public interface UserService extends BaseService<User> {

    /**
     * 获取用户信息列表
     *
     * @param queryRequest
     * @return
     */
    List<User> listUser(UserQueryRequest queryRequest);

    /**
     * 分页获取用户信息
     *
     * @param pageRequest
     * @return
     */
    Page<User> getUserPage(UserPageRequest pageRequest);

    /**
     * 按用户名获取用户信息
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 修改用户信息
     *
     * @param updateRequest
     * @return
     */
    Boolean updateUser(UserSelfUpdateRequest updateRequest);
}
