package com.collaborator.collaborator.backend.services.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.collaborator.collaborator.backend.services.MyUserDetailsService;
import com.collaborator.collaborator.backend.services.failure.CustomAuthFailure;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private CustomAuthFailure failureHandler;

    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/register", "/login", "/posts", "/not-authorized", "/create-post").permitAll()
                               .requestMatchers(req-> isVaadinInternalRequest(req)).permitAll()
                                 .requestMatchers(   "/VAADIN/**",
                                                    "/frontend/**",
                                                    "/images/**",
                                                    "/manifest.webmanifest",
                                                    "/sw.js",
                                                    "/offline.html",
                                                    "/register" , "/profile").permitAll()
                                .requestMatchers("/admin/**").authenticated()
                                .anyRequest().authenticated()
                )
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                       // .formLogin(form -> form.failureHandler(failureHandler).permitAll())
                        
                        // .logout(logout -> logout
                        //     .logoutSuccessUrl("/login?logout")
                        //     .permitAll());  
                        
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

 @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static boolean isVaadinInternalRequest(HttpServletRequest req){
                String param = req.getParameter("v-r");

                return param != null;
    }
   
    
}
