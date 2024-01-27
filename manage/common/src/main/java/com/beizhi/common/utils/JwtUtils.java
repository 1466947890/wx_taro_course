package com.beizhi.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.entity.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class JwtUtils {
    private static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "beizhi50239";


    /**
     * 生成token
     * @param user
     * @return
     */
    public static String sign(User user){
        String token = null;
        Date expireAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        log.info(String.format("过期时间【%s】", format.format(expireAt)));
        token = JWT.create()
                .withIssuer("auth0")
                .withClaim("userId", user.getId())
                .withClaim("openid", user.getOpenId())
                .withExpiresAt(expireAt).sign(Algorithm.HMAC256(TOKEN_SECRET));
        return token;
    }


    public static Boolean verify(String token){
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return false;
        }
        return true;
    }

    public static Integer getUserId(String token){
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("userId").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Integer getUserIdByRequest(HttpServletRequest request){
        String token = request.getHeader("token");
        if(token == null){
            throw new BusinessException(BaseErrorEnum.TOKEN_ERROR);
        }
        return JwtUtils.getUserId(token);
    }
}
