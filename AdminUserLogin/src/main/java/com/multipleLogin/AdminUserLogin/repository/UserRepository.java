package com.multipleLogin.AdminUserLogin.repository;

import com.multipleLogin.AdminUserLogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
}
