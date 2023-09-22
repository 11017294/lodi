package com.lodi.xo.service.impl;

import com.lodi.common.core.service.impl.BaseServiceImpl;
import com.lodi.common.model.entity.User;
import com.lodi.xo.mapper.UserMapper;
import com.lodi.xo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户 服务层实现
 * @author MaybeBin
 * @createDate 2023-09-20
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}