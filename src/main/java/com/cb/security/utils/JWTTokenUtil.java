package com.cb.security.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cb.config.CommonConfig;
import com.cb.config.JWTConfig;
import com.cb.model.entity.User;
import com.cb.security.entity.AuthUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * @program: CommonBackground
 * @description:
 * @author:
 * @create: 2020-11-05 15:51
 **/
@Slf4j
public class JWTTokenUtil {

    /**
     * 创建Token
     *
     * @param user 用户信息
     * @return
     */
    public static String createAccessToken(AuthUser user) {

        String token = Jwts.builder().setId(// 设置JWT
                String.valueOf(user.getId())) // 用户Id
                .setSubject(user.getUsername()) // 用户名
                .setIssuedAt(new Date()) // 签发时间
                .setIssuer(CommonConfig.servername) // 签发者
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration)) // 过期时间
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret) // 签名算法、密钥
                //.setClaims(claims)
                .claim("authorities", JSON.toJSONString(user.getAuthorities()))
                .claim("LOGIN_IP",user.getLoginIp())
                .compact();
        return JWTConfig.tokenPrefix + token;
    }

    /**
     * 解析Token
     *
     * @param token Token信息
     * @return
     */
    public static AuthUser parseAccessToken(String token) {
        AuthUser user = null;
        if (StringUtils.isNotEmpty(token)) {
            try {
                // 去除JWT前缀
                token = token.substring(JWTConfig.tokenPrefix.length());

                // 解析Token
                Claims claims = Jwts.parser().setSigningKey(JWTConfig.secret).parseClaimsJws(token).getBody();

                // 获取用户信息
                user = new AuthUser();
                user.setId(Long.parseLong(claims.getId()));
                user.setUsername(claims.getSubject());
                user.setLoginIp(claims.get("LOGIN_IP").toString());

                // 获取角色
                Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                String authority = claims.get("authorities").toString();
                if (StringUtils.isNotEmpty(authority)) {
                    List<Map<String, String>> authorityList = JSON.parseObject(authority,
                            new TypeReference<List<Map<String, String>>>() {
                            });
                    for (Map<String, String> role : authorityList) {
                        if (!role.isEmpty()) {
                            authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                }
                user.setAuthorities(authorities);
            } catch (Exception e) {
                log.error("解析Token异常：" + e);
            }
        }
        return user;
    }

}
