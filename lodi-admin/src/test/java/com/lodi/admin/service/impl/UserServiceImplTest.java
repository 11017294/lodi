package com.lodi.admin.service.impl;


import com.lodi.common.model.entity.User;
import com.lodi.xo.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MaybeBin
 * @createDate 2023-09-20
 */
@Slf4j
@SpringBootTest
public class UserServiceImplTest {

    @Resource
    UserServiceImpl userService;

    @Test
    public void test() {
        List<User> list = userService.list();
    }

}
