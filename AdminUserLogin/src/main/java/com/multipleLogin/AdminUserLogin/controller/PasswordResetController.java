package com.multipleLogin.AdminUserLogin.controller;

import com.multipleLogin.AdminUserLogin.AOPutility.RateLimit;
import com.multipleLogin.AdminUserLogin.service.PasswordResetService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/forgot-password")
    public String getForgotPage(){
        return "user/forgot-password";
    }
    @PostMapping("/forgot-password")
    @RateLimit
    public String forgotPassword(@RequestParam("email") String email, HttpSession h) throws Throwable {
        passwordResetService.requestPasswordReset(email);
        return "user/user_confirmation";
    }

    @GetMapping("/reset-password")
    public String getResetPasswordPage(HttpSession session, HttpServletRequest request){
        String tk = request.getParameter("token");
        session.setAttribute("token", tk);
        return "user/reset_password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("newPassword") String newPassword, HttpSession s) throws Throwable {
        passwordResetService.resetPassword((String) s.getAttribute("token"), newPassword);
        return "redirect:/";
    }

}

