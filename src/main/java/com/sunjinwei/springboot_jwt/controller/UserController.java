package com.sunjinwei.springboot_jwt.controller;

import com.sunjinwei.springboot_jwt.domain.User;
import com.sunjinwei.springboot_jwt.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: com.sunjinwei.springboot_jwt.controller
 * @author: sun jinwei
 * @create: 2023-07-15 21:48
 * @description:
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public User login(User user) {

        String username = "tom";
        String password = "123456";

        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            // 创建token
            user.setToken(JwtUtil.createJwt());
            return user;
        }
        return null;
    }

    @GetMapping("/check")
    public boolean check(@RequestHeader("jwtToken") String jwtToken) {
        return JwtUtil.checkJwt(jwtToken);
    }

}