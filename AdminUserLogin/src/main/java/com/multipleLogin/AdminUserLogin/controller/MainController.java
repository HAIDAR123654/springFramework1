package com.multipleLogin.AdminUserLogin.controller;

import com.multipleLogin.AdminUserLogin.entity.User;
import com.multipleLogin.AdminUserLogin.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/home")
    public String viewAdminHomePage(){
        return "admin/admin_home";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/home")
    public String viewUserHomePage(Principal p, ModelMap m){
        User u = userRepository.findByEmail(p.getName());
        m.addAttribute("name", u.getName());
        return "user/user_home";
    }
    @GetMapping("/user/profile")
    public String viewUserHomePage(ModelMap m, Principal p, HttpSession h){
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
