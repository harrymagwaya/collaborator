package com.collaborator.collaborator.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collaborator.collaborator.backend.models.Post;
import com.collaborator.collaborator.backend.services.PostService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    private JdbcTemplate jdbcTemplate;

    @PostMapping("/create-post")
    public ResponseEntity<Post> postMethodName(@RequestBody Post post) {
        Post savedPost = postService.savePost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @GetMapping("/all-posts") // returns all posts from the partner repo
    public ResponseEntity<List<Post>> getAllUserPosts() {
        List<Post> post = postService.getAllPosts();
        return ResponseEntity.ok(post);
    }

    @GetMapping("/user-posts") // returns only posts from the users
    public List<Map<String, Object>> getAllCollabPosts() {
        String sql = "SELECT * FROM USER_POSTS";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/admin-posts") // returns only posts from the admin
    public List<Map<String, Object>> getAllAdminPosts(@RequestParam String param) {
        String sql = "SELECT * FROM ADMIN_POSTS";
        return jdbcTemplate.queryForList(sql);
    }
    

}
