package com.smart.starter.core.security.jwt;


import com.smart.starter.autoconfigure.security.SmartSecurityProperties;
import com.smart.starter.core.security.exception.SmartSecurityException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt操作
 * @author guwenchang
 * @date 2019-04-22 15:49
 */
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("WeakerAccess")
public class JwtOperator {
    public static final String USER_ID = "userId";
    public static final String USERNAME = "username";
    public static final String PERMS = "perms";
    private final SmartSecurityProperties smartSecurityProperties;

    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(this.smartSecurityProperties.getJwt().getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.error("Token Invalided", e);
            throw new SmartSecurityException("Token Invalided ", e);
        }
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + smartSecurityProperties.getJwt().getExpirationInSecond() * 1000);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USER_ID, user.getUserId());
        claims.put(USERNAME, user.getUsername());
        claims.put(PERMS, user.getPerms());
        Date createdTime = new Date();
        Date expirationTime = this.getExpirationTime();

        byte[] keyBytes = this.smartSecurityProperties.getJwt().getSecret().getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdTime)
                .setExpiration(expirationTime)
                .signWith(key)
                .compact();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}