package com.collaborator.collaborator.models;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserCollab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String userName;

    private String email;

    @NonNull
    private String password;

    @NonNull
    private String areaOfExpertise;

    private String description;

    private String location;

    private String providerPicUrl; 

    @NonNull
    private boolean isEnabled = false;

}
