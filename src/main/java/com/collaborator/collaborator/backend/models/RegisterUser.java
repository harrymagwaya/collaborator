package com.collaborator.collaborator.backend.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class RegisterUser {

    public RegisterUser() {
        //TODO Auto-generated constructor stub
    }

    @NonNull
    private String userName;

    @NonNull
    private String email;

    @NonNull
    private String areaOfExpertise;
    
    @NonNull
    private String password;

    @NonNull
    private String location;

}
