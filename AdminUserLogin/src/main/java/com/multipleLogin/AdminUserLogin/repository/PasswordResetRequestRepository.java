package com.multipleLogin.AdminUserLogin.repository;

import com.multipleLogin.AdminUserLogin.entity.PasswordResetRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRequestRepository extends JpaRepository<PasswordResetRequest, Long> {

    public PasswordResetRequest findByToken(String token);
}
