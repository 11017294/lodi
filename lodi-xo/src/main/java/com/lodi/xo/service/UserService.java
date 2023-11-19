package com.lodi.xo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.service.BaseService;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.user.UserPageRequest;
import com.lodi.common.model.request.user.UserQueryRequest;

import java.util.List;

/**
 * 用户 服务层
 *
 * @author MaybeBin
 * @createDate 2023-09-20
 */
public interface UserService extends BaseService<User> {

    List<User> listUser(UserQueryRequest userQueryRequest);

    Page<User> getUserPage(UserPageRequest userPageRequest);

    User getUserByUsername(String username);

}
