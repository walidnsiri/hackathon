package com.example.matchmaking.service;

import com.example.matchmaking.configuration.util.CookieUtil;
import com.example.matchmaking.configuration.util.JwtTokenUtil;
import com.example.matchmaking.domain.dto.Token;
import com.example.matchmaking.domain.exception.NotFoundException;
import com.example.matchmaking.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
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

    public Map<String,Object> loggedin(String accessToken, String refreshToken){

        if (!StringUtils.hasText(accessToken) && !StringUtils.hasText(refreshToken)) throw new NotFoundException("Cookie not found, please login!");

        boolean accessTokenIsValid = jwtTokenUtil.validate(accessToken);
        boolean refreshTokenIsValid = jwtTokenUtil.validate(refreshToken);

        Map<String,Object> map=new HashMap<>();
        HttpHeaders httpHeaders = new HttpHeaders();


        if (!refreshTokenIsValid && accessTokenIsValid) throw new ValidationException("cookie not valid");
        if (!accessTokenIsValid && !refreshTokenIsValid)  throw new NotFoundException("cookie not valid");
        if (!accessTokenIsValid) {
            User user = loadUserFromToken(refreshToken);
            Token newAccessToken = jwtTokenUtil.generateAccessToken(user);
            addAccessTokenCookie(httpHeaders,newAccessToken);
            map.put("user", user);
            map.put("headers", httpHeaders);
            return map;
        }

        User user = loadUserFromToken(refreshToken);
        map.put("user", user);
        map.put("headers", httpHeaders);
        return map;
    }

}
