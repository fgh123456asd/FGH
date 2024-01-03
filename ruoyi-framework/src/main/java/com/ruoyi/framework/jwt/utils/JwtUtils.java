package com.ruoyi.framework.jwt.utils;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * jwt 工具类
 * 
 * @author ruoyi
 */
public class JwtUtils
{
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    private static final String CLAIM_KEY = "key";

    private static final String SECRET = "com.ruoyi.framework.jwt.utils.JwtUtils";

    public static String createToken(String key)
    {
        return createToken(key, SECRET, EXPIRE_TIME);
    }

    public static String createToken(String key, String password)
    {
        return createToken(key, password, EXPIRE_TIME);
    }

    public static String createToken(String key, String password, long expireTime)
    {
        Date date = new Date(System.currentTimeMillis() + expireTime);
        // 加密处理密码
        Algorithm algorithm = Algorithm.HMAC256(password);
        return JWT.create().withClaim(CLAIM_KEY, key).withExpiresAt(date).sign(algorithm);
    }

    public static void verify(String key, String token){
        verify(key,SECRET,token);
    }

    public static void verify(String key, String dbPwd, String token)
    {
        Algorithm algorithm = Algorithm.HMAC256(dbPwd);
        JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim(CLAIM_KEY, key).build();
        try
        {
            jwtVerifier.verify(token);
        }
        catch (TokenExpiredException e)
        {
            throw new TokenExpiredException("token已过期");
        }
        catch (JWTVerificationException e)
        {
            throw new JWTVerificationException("token验证失败");
        }
    }

    public static String getKey(String token)
    {
        try
        {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(CLAIM_KEY).asString();
        }
        catch (JWTDecodeException e)
        {
            return null;
        }
    }
}
