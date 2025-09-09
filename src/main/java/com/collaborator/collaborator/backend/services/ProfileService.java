package com.collaborator.collaborator.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.collaborator.collaborator.backend.models.MyUserDetails;
import com.collaborator.collaborator.backend.models.UserCollab;
import com.collaborator.collaborator.backend.repositories.UserCollabRepository;

@Service
public class ProfileService {

    @Autowired
    private UserCollabRepository userCollabRepository;


    public UserCollab editProfile(UserCollab updatedUser){
        UserCollab user = userCollabRepository.findByEmail(getCurrentUserName())
                                                .orElseThrow(()-> new RuntimeException("user doesnt exist"));

        updatedUser.setDescription(user.getDescription());
        updatedUser.setAreaOfExpertise(user.getAreaOfExpertise());
        updatedUser.setLocation(user.getLocation());

        return userCollabRepository.save(updatedUser);
    }
    
    public String getCurrentUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("user is not authenticated");
        }
        else{
            MyUserDetails userDetails = (MyUserDetails)authentication.getPrincipal();
            return userDetails.getUser().getEmail();
        }
    }
    
}
