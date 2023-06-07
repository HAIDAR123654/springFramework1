package com.multipleLogin.AdminUserLogin.database;

import com.multipleLogin.AdminUserLogin.entity.Role;
import com.multipleLogin.AdminUserLogin.entity.User;
import com.multipleLogin.AdminUserLogin.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseLoader {
    private UserRepository repo;

    public DatabaseLoader(UserRepository repo) {
        this.repo = repo;
    }

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            User user1 = new User("Admin","admin@codejava.net", "admin123", Role.ADMIN);
            User user2 = new User("David","david@gmail.com", "david123", Role.ADMIN);
            User user3 = new User("Alex","alex@gmail.com", "alex123", Role.USER);
            User user4 = new User("Jane","jane@gmail.com", "jane123", Role.USER);
            repo.saveAll(List.of(user1, user2, user3, user4));
            System.out.println("Sample database initialized");
        };
    }
}