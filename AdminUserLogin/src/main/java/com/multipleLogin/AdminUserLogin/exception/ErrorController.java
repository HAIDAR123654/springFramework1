package com.multipleLogin.AdminUserLogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String exception(final Throwable throwable, final Model model) {
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    @ExceptionHandler({SessionTokenExpire.class, RateLimitException.class})
    public String sessionTokenExpire(Exception ex, Model m){
        String errorMessage;
        if(ex instanceof SessionTokenExpire){
            errorMessage = ex.getMessage();
        }
        else {
            RateLimitException rx = (RateLimitException) ex;
            errorMessage = rx.getMessage();
        }
        m.addAttribute("errorMessage", errorMessage);
        return "token-expire";
    }

}
