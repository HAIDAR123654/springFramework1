package com.multipleLogin.AdminUserLogin.service;

import com.multipleLogin.AdminUserLogin.entity.PasswordResetRequest;
import com.multipleLogin.AdminUserLogin.entity.User;
import com.multipleLogin.AdminUserLogin.exception.RateLimitException;
import com.multipleLogin.AdminUserLogin.exception.SessionTokenExpire;
import com.multipleLogin.AdminUserLogin.repository.PasswordResetRequestRepository;
import com.multipleLogin.AdminUserLogin.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    PasswordResetRequestRepository passwordResetRequestRepository;
    @Autowired
    UserRepository userRepository;
    Date expirationDate;
    private final Map<String, Long> requests = new HashMap<>();

    public void requestPasswordReset(String email) throws Throwable {

        Long lastRequestTime = requests.getOrDefault(email, 0L);
        long now = System.currentTimeMillis();

        int MAX_REQUESTS_PER_MINUTE = 1;
        if (now - lastRequestTime < 60000 / MAX_REQUESTS_PER_MINUTE) {
            throw new RateLimitException("Rate limit exceeds");
        }

        requests.put(email, now);

//-------------------------------------------------------------------------

        if(userRepository.findByEmail(email) == null){
            throw new UsernameNotFoundException("User name not exist..");
        }
        // Generate a random token
        String token = UUID.randomUUID().toString();

        //set expire time 1 hour from now
        expirationDate = new Date(System.currentTimeMillis() + 60000);

        // Save the token in the database
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
        passwordResetRequest.setEmail(email);
        passwordResetRequest.setToken(token);
        passwordResetRequest.setExpirationDate(expirationDate);
        passwordResetRequestRepository.save(passwordResetRequest);

        // Email the user with a link to reset their password
        String resetLink = "http://localhost:8080/reset-password?token=" + token;
        MimeMessage message  = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("ha8905045@gmail.com");
        helper.setTo(email);
        helper.setSubject("Reset your password");
        helper.setText("To reset your password, click on the following link: " + resetLink);
        javaMailSender.send(message);

    }

    public void resetPassword(String token, String newPassword) throws Throwable {
        // Find the PasswordResetRequest entity with the given token

        PasswordResetRequest passwordResetRequest = passwordResetRequestRepository.findByToken(token);
        Date currentTime = new Date();

        if(currentTime.after(expirationDate)){
            passwordResetRequestRepository.delete(passwordResetRequest);
            throw new SessionTokenExpire("token is expired");
        }
        // Reset the user's password
        User user = userRepository.findByEmail(passwordResetRequest.getEmail());
        user.setPassword(newPassword);
        userRepository.save(user);

        // Delete the PasswordResetRequest entity
        passwordResetRequestRepository.delete(passwordResetRequest);
    }
}
