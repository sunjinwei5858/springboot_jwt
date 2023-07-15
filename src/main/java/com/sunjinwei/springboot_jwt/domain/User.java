package com.sunjinwei.springboot_jwt.domain;

import lombok.Data;

/**
 * @program: com.sunjinwei.springboot_jwt.domain
 * @author: sun jinwei
 * @create: 2023-07-15 21:47
 * @description:
 **/
@Data
public class User {

    private String username;

    private String password;

    private String token;
}