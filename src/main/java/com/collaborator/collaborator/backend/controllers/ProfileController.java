package com.collaborator.collaborator.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collaborator.collaborator.backend.models.UserCollab;
import com.collaborator.collaborator.backend.services.ProfileService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PutMapping("/edit")
    public ResponseEntity<UserCollab> updateProfile(@RequestBody UserCollab updatedUser) {
        UserCollab savedUser = profileService.editProfile(updatedUser);
        return ResponseEntity.ok(savedUser);
    }
    
}
