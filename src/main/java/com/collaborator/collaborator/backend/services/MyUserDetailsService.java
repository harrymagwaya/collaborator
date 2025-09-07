package com.collaborator.collaborator.backend.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.collaborator.collaborator.backend.models.MyUserDetails;
import com.collaborator.collaborator.backend.models.UserCollab;
import com.collaborator.collaborator.backend.repositories.UserCollabRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserCollabRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCollab user =  userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("'USER NOT FOUND'") );
        
        return new MyUserDetails(user);
    }

    
    
}
