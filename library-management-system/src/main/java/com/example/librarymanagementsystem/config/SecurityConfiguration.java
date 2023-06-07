package com.example.librarymanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .antMatchers("/edit/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.PUBLISHER.name())
                .antMatchers("/delete/**").hasRole(UserRole.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin();


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails admin = User.withUsername("user_admin")
                .password("{noop}1234")
                .roles(UserRole.ADMIN.name())
                .build();
        UserDetails publisher = User.withUsername("user_publisher")
                .password("{noop}123")
                .roles(UserRole.PUBLISHER.name())
                .build();
        UserDetails readOnlyUser = User.withUsername("user_read_only")
                .password("{noop}12")
                .roles(UserRole.READ_ONLY.name())
                .build();
        return new InMemoryUserDetailsManager(admin,publisher,readOnlyUser);
    }

}
