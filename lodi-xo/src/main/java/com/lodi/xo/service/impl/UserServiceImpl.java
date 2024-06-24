package com.lodi.xo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.core.enums.ErrorCode;
import com.lodi.common.core.exception.BusinessException;
import com.lodi.common.model.convert.user.UserConvert;
import com.lodi.common.model.entity.Comment;
import com.lodi.common.model.request.user.UserSelfUpdateRequest;
import com.lodi.common.mybatis.service.impl.BaseServiceImpl;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.request.user.UserPageRequest;
import com.lodi.common.model.request.user.UserQueryRequest;
import com.lodi.common.satoken.utils.LoginHelper;
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
        return baseMapper.selectPage(new Page<>(pageRequest.getCurrentPage(), pageRequest.getPageSize()), queryWrapper);
    }

    @Override
    public User getUserByUsername(String username) {
        return baseMapper.selectByUsername(username);
    }

    @Override
    public Boolean updateUser(UserSelfUpdateRequest updateRequest) {
        User user = UserConvert.INSTANCE.toEntity(updateRequest);
        // 检查是否当前用户或管理员
        checkCurrentUserOrAdmin(user.getId());
        return updateById(user);
    }

    /**
     * 检查是否当前用户或管理员
     *
     * @param userId 用户id
     */
    private void checkCurrentUserOrAdmin(Long userId) {
        if (userId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        LoginHelper.checkLoginUserOrAdmin(user.getId());
    }

}
