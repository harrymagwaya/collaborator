package com.collaborator.collaborator.backend.models;

import lombok.Data;

@Data
public class LoginRequest {
    
    private String username;

    private String pasword;
}
