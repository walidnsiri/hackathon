package com.example.matchmaking.service;

import com.example.matchmaking.configuration.util.CookieUtil;
import com.example.matchmaking.configuration.util.JwtTokenUtil;
import com.example.matchmaking.domain.dto.Token;
import com.example.matchmaking.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final CookieUtil cookieUtil;
    private final UserService userService;

    public void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

    public void addRefreshTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createRefreshTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

    public void deleteTokenCookies(HttpHeaders httpHeaders) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.deleteAccessTokenCookie().toString());
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.deleteRefreshTokenCookie().toString());
    }

    public HttpHeaders handleLoginTokens(User user) {
        Token newAccessToken;
        Token newRefreshToken;
        HttpHeaders httpHeaders = new HttpHeaders();
        newAccessToken = jwtTokenUtil.generateAccessToken(user);
        newRefreshToken = jwtTokenUtil.generateRefreshToken(user);
        addAccessTokenCookie(httpHeaders,newAccessToken);
        addRefreshTokenCookie(httpHeaders,newRefreshToken);
        return httpHeaders;
    }

    public Token refresh(String accessToken, String refreshToken) {
        String username= jwtTokenUtil.getRefreshTokenUsernameIfValid(accessToken,refreshToken);
        User user = userService.getUserByUsername(username);
        Token newAccessToken = jwtTokenUtil.generateAccessToken(user);

        return newAccessToken;
    }

    public User loadUserFromToken(String token) {
        String username = jwtTokenUtil.getUsername(token);
        User user = userService.getUserByUsername(username);
        return user;
    }

}
