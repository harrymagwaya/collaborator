package com.collaborator.collaborator.backend.services;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.collaborator.collaborator.backend.dto.PostWithAuthorDTO;
import com.collaborator.collaborator.backend.dto.PostWithAuthorInfo;
import com.collaborator.collaborator.backend.models.MyUserDetails;
import com.collaborator.collaborator.backend.models.Post;
import com.collaborator.collaborator.backend.models.UserCollab;
import com.collaborator.collaborator.backend.repositories.PostRepository;
import com.collaborator.collaborator.backend.repositories.UserCollabRepository;

import jakarta.transaction.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserCollabRepository userCollabRepository;

    //private UserCollab user = new UserCollab();



    public Post savePost(Post post){
        UserCollab user = userCollabRepository.findByEmail(getCurrentUserName())
                                            .orElseThrow(()->new RuntimeException("user doesnt exist"));

        post.setAuthor(user);
    
        return postRepository.save(post);
    }

    @Transactional
    public List<PostWithAuthorDTO> getAllPosts(){
        return postRepository.findAllPostWithAuthor();
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
