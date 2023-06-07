package com.codejava.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }


    @GetMapping("/admin/login")
    public String viewAdminLoginPage(){
        return "admin/admin_login";
    }
    @GetMapping("/user/login")
    public String viewUserLoginPage(){
        return "user/user_login";
    }


    @GetMapping("/admin/home")
    public String viewAdminHomePage(){
        return "admin/admin_home";
    }

    @GetMapping("/user/home")
    public String viewUserHomePage(){
        return "user/user_home";
    }
    @GetMapping("/user/profile")
    public String viewUserHomePage(ModelMap m, HttpSession h, Principal p){
        User u = userRepository.findByEmail(p.getName());
        m.addAttribute("username",u.getEmail());
        m.addAttribute("password",u.getPassword());
        m.addAttribute("role",u.getRole());
        return "user/user_profile";
    }
    @GetMapping("/admin/allUsers")
    public String getAllUsers(ModelMap m){
        List<User> allUser = userRepository.findAll();
        m.addAttribute("allUser",allUser);
        return "admin/all_users";
    }
}
