package com.codejava.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/user/register")
    public ResponseEntity<User> viewRegisterPage(@RequestBody User user){
        User createdUser = userRepository.save(user);
        return ResponseEntity.ok(createdUser);
    }
}
