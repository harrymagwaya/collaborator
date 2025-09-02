package com.collaborator.collaborator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.collaborator.collaborator.models.UserCollab;
import com.collaborator.collaborator.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserCollab user) {
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

    @GetMapping("/login")
    public String login(@RequestParam String param) {
        return new String();
    }
    
    

    
}
