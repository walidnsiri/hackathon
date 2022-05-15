package com.example.matchmaking.configuration.filters;


import com.example.matchmaking.configuration.util.CookieUtil;
import com.example.matchmaking.configuration.util.JwtTokenUtil;
import com.example.matchmaking.domain.dto.Token;
import com.example.matchmaking.domain.model.User;
import com.example.matchmaking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.List.of;
import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${authentication.accessTokenCookieName}")
    private String accessTokenCookieName;

    @Value("${authentication.refreshTokenCookieName}")
    private String refreshTokenCookieName;

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepo;
    private final CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        try {

            final Map<String,String> tokensmap = getJwtFromCookie(request);
            if(!tokensmap.isEmpty()){
            String accessToken = tokensmap.get("accessToken");
            String refreshToken = tokensmap.get("refreshToken");

            if (StringUtils.hasText(accessToken) && jwtTokenUtil.validate(accessToken)) {
                authenticate(request,accessToken);
                //chain.doFilter(request, response);
            }
            else if (StringUtils.hasText(refreshToken) && jwtTokenUtil.validate(refreshToken)) {
                //generate new access token
                User user = new User();
                user.setId(new ObjectId(jwtTokenUtil.getUserId(refreshToken)));
                user.setUsername(jwtTokenUtil.getUsername(refreshToken));

                Token newAccesstoken = jwtTokenUtil.generateAccessToken(user);
                //authenticate request
                response.setHeader(HttpHeaders.SET_COOKIE,cookieUtil.createAccessTokenCookie(newAccesstoken.getTokenValue(), newAccesstoken.getDuration()).toString());
                authenticate(request,newAccesstoken.getTokenValue());
            }
            }
           // authenticate(request,jwtTokenUtil.getRefreshTokenUsernameIfValid(accessToken,refreshToken));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        chain.doFilter(request, response);
    }

    private Map<String,String> getJwtFromCookie(HttpServletRequest request) {
        Map<String,String> tokensmap = new HashMap<>();
        String accessToken = "";
        String refreshToken = "";
        Cookie[] cookies = request.getCookies();
        if(!Objects.isNull(cookies)) {
        for (Cookie cookie : cookies) {
            if (accessTokenCookieName.equals(cookie.getName())) {
                accessToken = cookie.getValue();
                tokensmap.put("accessToken",accessToken);
            }
            if (refreshTokenCookieName.equals(cookie.getName())) {
                refreshToken = cookie.getValue();
                tokensmap.put("refreshToken",refreshToken);
            }
        }}
        return tokensmap;
    }

        public void authenticate  (HttpServletRequest request, String token) {

            UserDetails userDetails = userRepo
                    .findByUsername(jwtTokenUtil.getUsername(token))
                    .orElse(null);


            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null,
                    ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(of())
            );

            authentication
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
}
