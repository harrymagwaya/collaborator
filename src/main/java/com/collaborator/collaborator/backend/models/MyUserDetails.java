package com.collaborator.collaborator.backend.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class MyUserDetails implements org.springframework.security.core.userdetails.UserDetails{

    private final UserCollab user;
    @NotEmpty
    private String email;

    @NotEmpty
    private String pasword;

    public MyUserDetails(UserCollab user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // @Override
    // public boolean isEnabled() {
    //     return user.isEnabled();
    // }

    
}
