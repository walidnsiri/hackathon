package com.example.matchmaking.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshResponse {

        private SuccessFailure status;
        private String message;

        public enum SuccessFailure {
            SUCCESS, FAILURE, OK
        }
}


