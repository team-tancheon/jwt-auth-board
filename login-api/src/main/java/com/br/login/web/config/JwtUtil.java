package com.br.login.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Base64;

public class JwtUtil {

    public static final String AUTH_HEADER = "Authentication";
    public static final String BEARER = "Bearer ";

    //TODO: SECRET_KEY, Algorithm 값 외부로 빼기
    private final String secretKey = Base64.getEncoder()
            .encodeToString("defuat-secret-key".getBytes());
    private final Algorithm algorithm = Algorithm.HMAC256(secretKey);

    private final long  accessExpTime= 30 * 1000L; //30초
    private final long  refreshExpTime= 30 * 60 * 1000L; // 30분

    public String createAccessToken(String userId){
        return BEARER + JWT.create().withSubject(userId)
                .withClaim("exp", System.currentTimeMillis() + accessExpTime)
                .sign(algorithm);
    }

    public VerifyResult verify(String token){
        try{
            DecodedJWT decode = JWT.require(algorithm).build().verify(token);
            return VerifyResult.builder().userId(decode.getSubject()).result(true).build();
        }catch (JWTVerificationException e){
            DecodedJWT decode = JWT.decode(token);
            return VerifyResult.builder().userId(decode.getSubject()).result(false).build();
        }
    }
}
