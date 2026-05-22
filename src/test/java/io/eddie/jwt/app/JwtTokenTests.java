package io.eddie.jwt.app;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
public class JwtTokenTests {

    @Test
    void it_will_issue_a_token() {

        Date cur = new Date();
        Date exp = new Date(cur.getTime() + 30_000L);

        String secretKeyStr = "019e4de2-d7ea-75f1-9c3e-b73f117dedd2-019e4de2-d7ea-7085-8926-ba0beabb64b3";

        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyStr.getBytes());

        JwtBuilder jwtBuilder = Jwts.builder()
                .subject("Hello, World!")
                .claim("name", "eddie")
                .issuedAt(cur)
                .expiration(exp)
                .signWith(secretKey);

        String token = jwtBuilder.compact();

        log.info("token = {}", token);

    }

}
