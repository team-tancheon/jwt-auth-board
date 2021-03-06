package com.hancom.authserver;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;


@AllArgsConstructor
@SpringBootTest
@Slf4j
public class JwtTokenTest {

    @DisplayName("1. JWT 토큰 생성 테스트")
    @Test
    void createJWT() throws InterruptedException {

        Algorithm algorithm = Algorithm.HMAC256("default-secret-key");
        String token = JWT.create()
                .withSubject("brryu")
                .withClaim("exp", Instant.now().getEpochSecond() + 3)
                .sign(algorithm);

//        DecodedJWT decode = JWT.decode(token);
        DecodedJWT decode = JWT.require(algorithm).build().verify(token);

        log.debug("{}",decode);

//        assertThrows(
//
//        );


    }
}
