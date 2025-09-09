package com.collaborator.collaborator.backend.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Data;

@Data
 public class PostWithAuthorDTO {
    private Long postId;
    private String postTitle;
    private String content;
    private String postType;
    private Timestamp createdAt;
    private String providerName;
    private String pictureUrl;

    public PostWithAuthorDTO(Long postId, String postTitle, String content, String postType,
                             Timestamp createdAt, String providerName, String pictureUrl) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.content = content;
        this.postType = postType;
        this.createdAt = createdAt;
        this.providerName = providerName;
        this.pictureUrl = pictureUrl;
    }

    // Getters only (or setters if needed)
}
 
