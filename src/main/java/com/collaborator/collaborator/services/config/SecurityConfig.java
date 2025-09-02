package com.collaborator.collaborator.services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.collaborator.collaborator.services.MyUserDetailsService;
import com.collaborator.collaborator.services.failure.CustomAuthFailure;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private CustomAuthFailure failureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
            http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register", "/api/users/status/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") 
                        .anyRequest().authenticated()                  
                        )
                        .sessionManagement((session)-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                        .formLogin(form -> form.failureHandler(failureHandler).permitAll())
                        
                        .logout(logout -> logout
                            .logoutSuccessUrl("/login?logout")
                            .permitAll());  
                        
                        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder())
                    .and()
                    .build();
    }



    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
