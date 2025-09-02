package com.collaborator.collaborator.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.collaborator.collaborator.models.MyUserDetails;
import com.collaborator.collaborator.models.UserCollab;
import com.collaborator.collaborator.repositories.UserCollabRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserCollabRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCollab user =  userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("'USER NOT FOUND'") );
        
        return new MyUserDetails(user);
    }

    
    
}
