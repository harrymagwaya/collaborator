package com.collaborator.collaborator.backend.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name = "service_posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @NonNull
    @Column(name = "post_title")
    private String title;
    
    @NonNull
    @Column(name = "content")
    private String postContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type")
    @NonNull
    private PostType postType;
   
    @Nonnull
    private Timestamp createdAt;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id ", nullable = false  )
    private UserCollab author;

    @PrePersist
    public void getDefaulTimestamp(){
        if (createdAt == null) {
            createdAt = Timestamp.valueOf(LocalDateTime.now());
        }
        postType = PostType.announcements;
    }

    public  enum PostType {
        NORMAL,
        INTERVENTION,
        announcements
    }

    public Post(){

    }

}

/*
 * @Route("create-post")
 * public class CreatePostForm extends VerticalLayout {
 * 
 * private final Binder<ServicePost> binder = new Binder<>(ServicePost.class);
 * 
 * public CreatePostForm(ServicePostService postService, ServiceProviderService
 * providerService) {
 * // Fields
 * TextField postTitle = new TextField("Post Title");
 * TextArea content = new TextArea("Content");
 * ComboBox<String> postType = new ComboBox<>("Post Type");
 * postType.setItems("announcements", "update", "event");
 * 
 * Button submit = new Button("Submit Post");
 * 
 * // Layout
 * FormLayout formLayout = new FormLayout(postTitle, content, postType);
 * add(new H2("Create a New Post"), formLayout, submit);
 * 
 * // Bind fields
 * binder.forField(postTitle).asRequired("Title is required").bind(ServicePost::
 * getPostTitle, ServicePost::setPostTitle);
 * binder.forField(content).asRequired("Content is required").bind(ServicePost::
 * getContent, ServicePost::setContent);
 * binder.forField(postType).asRequired("Type is required").bind(ServicePost::
 * getPostType, ServicePost::setPostType);
 * 
 * // Submit logic
 * submit.addClickListener(event -> {
 * ServicePost post = new ServicePost();
 * if (binder.writeBeanIfValid(post)) {
 * // 🔐 Get authenticated user's email
 * String email =
 * SecurityContextHolder.getContext().getAuthentication().getName();
 * 
 * // 🔍 Lookup provider by email
 * Optional<ServiceProvider> providerOpt = providerService.findByEmail(email);
 * 
 * if (providerOpt.isPresent()) {
 * post.setAuthor(providerOpt.get());
 * postService.save(post);
 * Notification.show("Post created successfully");
 * } else {
 * Notification.show("Authenticated user is not a registered provider", 3000,
 * Notification.Position.MIDDLE);
 * }
 * } else {
 * Notification.show("Please fill all required fields", 3000,
 * Notification.Position.MIDDLE);
 * }
 * });
 * }
 * }
 * 
 */

