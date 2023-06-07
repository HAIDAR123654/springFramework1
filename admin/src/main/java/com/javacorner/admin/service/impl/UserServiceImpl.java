package com.javacorner.admin.service.impl;

import com.javacorner.admin.dao.RoleDao;
import com.javacorner.admin.dao.UserDao;
import com.javacorner.admin.entity.Role;
import com.javacorner.admin.entity.User;
import com.javacorner.admin.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    private final RoleDao roleDao;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserDao userDao, RoleDao roleDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password) {
        User user = loadUserByEmail(email);
        if(user != null) throw new RuntimeException("User with email : " + email + "already exist");
        String encodedPassword = passwordEncoder.encode(password);
        return userDao.save(new User(email, encodedPassword));
    }

    @Override
    public void assignRoleToUser(String email, String roleName) {
        User user =loadUserByEmail(email);
        Role role = roleDao.findByName(roleName);
        user.assignRoleToUser(role);
    }

    @Override
    public boolean doesCurrentUserHasRole(String roleName) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
    }
}

