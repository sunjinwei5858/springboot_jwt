package com.sunjinwei.springboot_jwt.test;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

/**
 * @program: com.sunjinwei.springboot_jwt.test
 * @author: sun jinwei
 * @create: 2023-07-15 21:03
 * @description:
 **/
public class JwtTest {

    public String signKey = "admin";

    /**
     * 1 创建jwt
     */
    @Test
    public void createJwt() {
        JwtBuilder builder = Jwts.builder();
        String jwtToken = builder
                // 1 Header 头部
                .setHeaderParam("typ", "jwt")
                .setHeaderParam("alg", "HS256")
                // 2 Payload 载荷
                .claim("username", "tom")
                .claim("role", "admin")
                .setSubject("admin-test")
                // token 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 1))
                // id字段
                .setId(UUID.randomUUID().toString())
                // sign 签名: 设置加密算法+签名
                .signWith(SignatureAlgorithm.HS256, signKey)
                // 使用 . 连接成一个完整的字符串
                .compact();
        // eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2ODk0MzAyMzMsImp0aSI6ImI5YTE5MWQxLWJhZWYtNDg3OC04YTczLTNlODE3MzQzZGZlMSJ9.mCYUyx7MWb4zUystCZNVXKRLFP0JBbBSnxN7e3c_R0c
        System.out.println(jwtToken);
    }

    /**
     * 2 验证jwt
     */
    @Test
    public void checkJwt() {

        String jwtToken = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2ODk0MzAyMzMsImp0aSI6ImI5YTE5MWQxLWJhZWYtNDg3OC04YTczLTNlODE3MzQzZGZlMSJ9.mCYUyx7MWb4zUystCZNVXKRLFP0JBbBSnxN7e3c_R0c";
        boolean result = Jwts.parser().isSigned(jwtToken);
        System.out.println(result);
    }


    /**
     * 3 解析jwt
     */
    @Test
    public void parseJwt() {
        String jwtToken = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE2ODk0MzAyMzMsImp0aSI6ImI5YTE5MWQxLWJhZWYtNDg3OC04YTczLTNlODE3MzQzZGZlMSJ9.mCYUyx7MWb4zUystCZNVXKRLFP0JBbBSnxN7e3c_R0c";
        // 1 获取jwt解析对象
        JwtParser parser = Jwts.parser();
        // 2 将jwtToken转化为一个key-value 通过key来获取
        Jws<Claims> claimsJws = parser.setSigningKey(signKey).parseClaimsJws(jwtToken);
        // 3 获取Jwt对象中的数据，get(key)表示根据key来获取value
        Claims claims = claimsJws.getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());

    }

}