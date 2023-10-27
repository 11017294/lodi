package com.lodi.xo.mapper;

import com.lodi.common.core.mapper.BaseMapper;
import com.lodi.common.model.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * 用户 数据层
 * @author MaybeBin
 * @createDate 2023-09-20
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from t_user where user_account = #{userAccount}")
    User selectByUserAccount(String userAccount);
}