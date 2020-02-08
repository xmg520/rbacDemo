package com.mzx.rbacdemo;

import com.mzx.rbacdemo.service.PermissionService;
import com.mzx.rbacdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class RbacdemoApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Test
    void contextLoads() {
//        System.out.println(userService.findAll());
        System.out.println(permissionService.findAll());
    }

}
