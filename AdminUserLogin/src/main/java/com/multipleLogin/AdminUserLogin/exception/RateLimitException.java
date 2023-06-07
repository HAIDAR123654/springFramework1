package com.multipleLogin.AdminUserLogin.exception;

public class RateLimitException extends Exception {
    public RateLimitException(String rateLimitExceeds) {
        super(rateLimitExceeds);
    }
}
