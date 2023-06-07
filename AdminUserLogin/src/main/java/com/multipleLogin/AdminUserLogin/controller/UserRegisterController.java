package com.multipleLogin.AdminUserLogin.controller;

import com.multipleLogin.AdminUserLogin.entity.User;
import com.multipleLogin.AdminUserLogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserRegisterController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String viewRegisterPage(){
        return "user/user_register";
    }
    @PostMapping("/register")
    public String RegisterUser(@ModelAttribute("user") User user){
        System.out.println(user);
        User createdUser = userRepository.save(user);
        System.out.println(createdUser);
        return "redirect:/";
    }

}
