package com.codejava.springsecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseLoader {
    private UserRepository repo;

    public DatabaseLoader(UserRepository repo){
        this.repo = repo;
    }

    @Bean
    public CommandLineRunner initializeDatabase(){
        return args -> {
           User user1 = new User("admin@codejava.net","admin123",Role.ADMIN);
           User user2 = new User("david@gmail.com","david123",Role.ADMIN);
           User user3 = new User("alex@gmail.com","alex123",Role.USER);
           User user4 = new User("jane@gmail.com","jane123",Role.USER);
           repo.saveAll(List.of(user1, user2, user3, user4));
           System.out.println("Sample database initialized");
        };
    }
}
