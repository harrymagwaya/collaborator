package com.collaborator.collaborator.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.collaborator.collaborator.backend.models.RegisterUser;
import com.collaborator.collaborator.backend.models.UserCollab;
import com.collaborator.collaborator.backend.repositories.UserCollabRepository;

@Service
public class UserService {
    
    @Autowired
    private UserCollabRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder encoder;
    
    public UserCollab registerUser(RegisterUser user) {
        if (userRepository.existsByEmail(user.getEmail())) {
             new IllegalAccessException("Email in use");
        }
        UserCollab provider = new UserCollab();
        provider.setPassword(encoder.encode(user.getPassword()));
        provider.setEmail(user.getEmail());
        provider.setAreaOfExpertise(user.getAreaOfExpertise());
        provider.setUserName(user.getUserName());
        provider.setEnabled(false);
        provider.setLocation(user.getLocation());
        return userRepository.save(provider);
    }

    public Boolean checkIfExists(String email){
        if (userRepository.existsByEmail(email)) {
            return true;
        }

        return false;
    }

    

    public boolean isApproved(String email) {

        return userRepository.findByEmail(email)
                            .map(UserCollab:: isEnabled)
                            .orElse(false);
    }
}
