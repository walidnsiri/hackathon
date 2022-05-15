package com.example.matchmaking.configuration.util;

import com.example.matchmaking.domain.dto.Token;
import com.example.matchmaking.domain.dto.TokenType;
import com.example.matchmaking.domain.exception.NotFoundException;
import com.example.matchmaking.domain.model.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${authentication.tokenSecret}")
    private String jwtSecret;
    @Value("${authentication.jwtIssuer}")
    private String jwtIssuer;
    @Value("${authentication.tokenExpirationMsec}")
    private Long tokenExpirationMsec;
    @Value("${authentication.refreshTokenExpirationMsec}")
    private Long refreshTokenExpirationMsec;


    private final Logger logger;

    public Token generateAccessToken(User user) {
        ZonedDateTime now = ZonedDateTime.now();
        Date nowDate = Date.from(now.toInstant());
        long duration = nowDate.getTime() + tokenExpirationMsec;
        Date expiryDate = new Date(duration);
        String token = Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getUsername()))
                .claim("user",user)
                .setIssuer(jwtIssuer)
                .setIssuedAt(nowDate)
                .setExpiration(expiryDate) // 10 minutes
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return new Token(TokenType.ACCESS,token, duration);
    }

    public Token generateRefreshToken(User user) {
        Date now = new Date();
        long duration = now.getTime() + refreshTokenExpirationMsec;
        Date expiryDate = new Date(duration);

        String token = Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(now)
                .setExpiration(expiryDate) // 7 days
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return new Token(TokenType.REFRESH,token, duration);
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    public String getRefreshTokenUsernameIfValid(String accessToken,String refreshToken) {

        //check if cookie exists!
        if (!StringUtils.hasText(accessToken) && !StringUtils.hasText(refreshToken)) throw new NotFoundException("Cookie not found, please login!");


        //check if cookie is valid
        boolean RefreshTokenIsValid = validate(refreshToken);
        if(!RefreshTokenIsValid) {
            throw new IllegalArgumentException("Refresh token is invalid, please login!");
        }

        return getUsername(refreshToken);
    }

}
