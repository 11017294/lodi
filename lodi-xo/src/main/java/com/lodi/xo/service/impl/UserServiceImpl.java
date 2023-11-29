package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.service.impl.BaseServiceImpl;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.user.UserPageRequest;
import com.lodi.common.model.request.user.UserQueryRequest;
import com.lodi.xo.mapper.UserMapper;
import com.lodi.xo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 服务层实现
 *
 * @author MaybeBin
 * @createDate 2023-09-20
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> listUser(UserQueryRequest queryRequest) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Page<User> getUserPage(UserPageRequest pageRequest) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectPage(new Page<>(pageRequest.getCurrent(), pageRequest.getPageSize()), queryWrapper);
    }

    @Override
    public User getUserByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

}
