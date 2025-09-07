package com.collaborator.collaborator.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.collaborator.collaborator.backend.models.LoginRequest;
import com.collaborator.collaborator.backend.models.RegisterUser;
import com.collaborator.collaborator.backend.models.UserCollab;
import com.collaborator.collaborator.backend.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/user")
@Profile("rest")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUser user) {
       
        if (userService.checkIfExists(user.getEmail())) {
           
            new RedirectView("/api/user/login");
            return ResponseEntity.ok("redirecting to login");
        }

         userService.registerUser(user);
        return ResponseEntity.ok("Successfull Awaiting Approval");
        
    }

    @GetMapping("/status/{username}")
    public ResponseEntity<String> checkStatus(@PathVariable String username) {

        boolean isEnabled = userService.isApproved(username);
        if (isEnabled) {
            new RedirectView("/login");
            return ResponseEntity.ok("Your account is approved");
              
        }
        return ResponseEntity.ok("Account pending");
    }

    @GetMapping("/register")
    public RedirectView login() {
        return new RedirectView("/register");
    }
     
}
