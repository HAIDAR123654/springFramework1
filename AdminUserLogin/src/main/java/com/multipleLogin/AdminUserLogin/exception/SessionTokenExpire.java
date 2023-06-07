package com.multipleLogin.AdminUserLogin.exception;

public class SessionTokenExpire extends Exception {
    public SessionTokenExpire(String tokenIsExpired) {
        super(tokenIsExpired);
    }
}
