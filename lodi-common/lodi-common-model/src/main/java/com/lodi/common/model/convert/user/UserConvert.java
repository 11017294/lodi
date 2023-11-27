package com.lodi.common.model.convert.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lodi.common.model.entity.User;
import com.lodi.common.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户 Convert
 *
 * @author MaybeBin
 * @createDate 2023-11-27
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserVO toVO(User user);

    List<UserVO> toVO(List<User> users);

    Page<UserVO> toVO(Page<User> userPage);
}
