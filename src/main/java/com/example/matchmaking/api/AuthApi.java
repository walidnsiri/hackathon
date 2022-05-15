package com.example.matchmaking.api;


import com.example.matchmaking.domain.dto.AuthRequest;
import com.example.matchmaking.domain.dto.RefreshResponse;
import com.example.matchmaking.domain.dto.Token;
import com.example.matchmaking.domain.model.User;
import com.example.matchmaking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;


    @PostMapping("login")
    public ResponseEntity<User> login(
            @RequestBody @Valid AuthRequest request
    ) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();
            HttpHeaders httpHeaders = authService.handleLoginTokens(user);


            return ResponseEntity.ok()
                    .headers(httpHeaders)
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("refresh")
    public ResponseEntity<RefreshResponse> refreshToken(
            @CookieValue(name="accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ) {
        Token newAccessToken = authService.refresh(accessToken, refreshToken);
        HttpHeaders httpHeaders = new HttpHeaders();
        authService.addAccessTokenCookie(httpHeaders,newAccessToken);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(new RefreshResponse(RefreshResponse.SuccessFailure.SUCCESS,"Token generated successfully!"));
    }

    @PostMapping("logged")
    public ResponseEntity<User> userLoggedIn(
            @CookieValue(name="accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ) {

        Map<String, Object> map = authService.loggedin(accessToken, refreshToken);
        User user = (User) map.get("user");


        return ResponseEntity.ok()
                .headers((HttpHeaders) map.get("headers"))
                .body(user);
    }

    @PostMapping("logout")
    public ResponseEntity<User> logout(//@RequestHeader HttpHeaders headers
    ) {
        HttpHeaders httpHeaders = new HttpHeaders();
        authService.deleteTokenCookies(httpHeaders);
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(null);
    }
}
