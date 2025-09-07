package com.collaborator.collaborator.backend.models;

import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "service_provider")
public class UserCollab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long providerId;

    @Column(name = "provider_name")
    private String userName;

    @NonNull
    @Column(name = "email")
    private String email;

    
    @Column(name = "password_")
    private String password;

   // @NonNull
    @Column(name = "area_of_expertise")
    private String areaOfExpertise;

    @Column(name = "description_")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "picture_url")
    private String pictureUrl; 

    @NonNull
    @Column(name = "is_enabled")
    private boolean isEnabled = false;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Post> posts;
}
