package com.collaborator.collaborator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.collaborator.collaborator.models.UserCollab;
import com.collaborator.collaborator.repositories.UserCollabRepository;

@Service
public class UserService {
    
    @Autowired
    private UserCollabRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserCollab registerUser(UserCollab user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        return userRepository.save(user);
    }


    public boolean isApproved(String username) {

        return userRepository.findByUserName(username)
                            .map(UserCollab:: isEnabled)
                            .orElse(false);
    }
}
