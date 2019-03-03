package com.xadevpos.demo.Util;


import com.xadevpos.demo.controller.AdminController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.alibaba.druid.sql.ast.SQLPartitionValue.Operator.List;

@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);


    @Value("${jwt.secret}")
    private String secret;

    /**
     * 根据负责生成JWT的token
     */
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}",token);
        }
        return claims;
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = (String)claims.get("username");
            //username =  claims.getSubject();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return username;
        }
        return username;
    }


    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJQZXJtaXNzaW9ucyI6W251bGwsIi9wbXMvcHJvZHVjdC9pbmRleCIsIi9wbXMvcHJvZHVjdC9hZGQiLCIvcG1zL3Byb2R1Y3QvdXBkYXRlUHJvZHVjdCIsIi9wbXMvcHJvZHVjdC9kZWxldGUiLCIvcG1zL3Byb2R1Y3RDYXRlL2luZGV4IiwiL3Btcy9wcm9kdWN0Q2F0ZS9jcmVhdGUiLCIvcG1zL3Byb2R1Y3RDYXRlL3VwZGF0ZSIsIi9wbXMvcHJvZHVjdEF0dHIvZGVsZXRlIiwiL3Btcy9icmFuZC9pbmRleCIsIi9wbXMvYnJhbmQvYWRkIiwiL3Btcy9icmFuZC91cGRhdGUiLCIvcG1zL2JyYW5kL2RlbGV0ZSJdLCJ1c2VybmFtZSI6eyJpZCI6MywidXNlcm5hbWUiOiJhZG1pbiIsInBhc3N3b3JkIjoiYWRtaW4iLCJpY29uIjoiaHR0cDovL21hY3JvLW9zcy5vc3MtY24tc2hlbnpoZW4uYWxpeXVuY3MuY29tL21hbGwvaW1hZ2VzLzIwMTkwMTI5LzE3MDE1N195SWwzXzE3Njc1MzEuanBnIiwiZW1haWwiOiJhZG1pbkAxNjMuY29tIiwibmlja05hbWUiOm51bGwsIm5vdGUiOiLns7vnu5_nrqHnkIblkZgiLCJjcmVhdGVUaW1lIjpudWxsLCJzdGF0dXMiOjF9fQ.waarkD7j55WHOn0x-3pBcSgJ_yNcSuFmCml-K0P3cTNfMmfC0PjgCvClg3HbGWPjrSC1ies5zho9v8d-DeKftA";
        Claims claims =Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody();

        List<String> permissions = (List)claims.get("Permissions");

    }


}
