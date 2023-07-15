package com.sunjinwei.springboot_jwt.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

/**
 * @program: com.sunjinwei.springboot_jwt.util
 * @author: sun jinwei
 * @create: 2023-07-15 21:51
 * @description:
 **/
public class JwtUtil {

    public static String SIGN_KEY = "admin";

    public static long EXPIRE_MILLIS = 1000 * 60 * 60;

    public static String createJwt() {
        JwtBuilder builder = Jwts.builder();
        return builder
                // 1 Header 头部
                .setHeaderParam("typ", "jwt")
                .setHeaderParam("alg", "HS256")
                // 2 Payload 载荷
                .claim("username", "tom")
                .claim("role", "admin")
                .setSubject("admin-test")
                // token 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_MILLIS))
                // id字段
                .setId(UUID.randomUUID().toString())
                // sign 签名: 设置加密算法+签名
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                // 使用 . 连接成一个完整的字符串
                .compact();
    }

    public static boolean checkJwt(String jwtToken) {

        return Jwts.parser().isSigned(jwtToken);

    }
}