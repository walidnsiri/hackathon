package com.example.matchmaking.domain.exception;

public class InvalidResetPasswordTokenException extends RuntimeException {
    public InvalidResetPasswordTokenException() {
        super();
    }

    public InvalidResetPasswordTokenException(String message){
        super(message);
    }

    public InvalidResetPasswordTokenException(String message, Throwable cause){
        super(message,cause);
    }

    public InvalidResetPasswordTokenException(Throwable cause) {
        super(cause);
    }
}
