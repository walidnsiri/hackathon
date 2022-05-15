package com.example.matchmaking.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {

    private TokenType tokenType;
    private String tokenValue;
    private Long duration;

}


