package io.eddie.jwt.service;

import io.eddie.jwt.config.properties.JwtProperties;
import io.eddie.jwt.dto.KeyPair;
import io.eddie.jwt.dto.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public KeyPair issueKeyPair(Long id, Role role) {

        String accessToken = issueAccessToken(id, role);
        String refreshToken = issueRefreshToken(id, role);

        return new KeyPair(accessToken, refreshToken);

    }

    public String issueAccessToken(Long id, Role role) {
        return issue(id, role, jwtProperties.getValidations().getAccess());
    }

    public String issueRefreshToken(Long id, Role role) {
        return issue(id, role, jwtProperties.getValidations().getRefresh());
    }

    private String issue(Long id, Role role, Long validTime) {
        return Jwts.builder()
                .subject(id.toString())
                .claim("role", role.getValue())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + validTime))
                .signWith(getSecretKey())
            .compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecrets().getAppKey().getBytes());
    }


}
