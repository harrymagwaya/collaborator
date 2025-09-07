package com.collaborator.collaborator.backend.dto;

import java.time.LocalDateTime;

public interface PostWithAuthorInfo {
    Long getPostId();

    String getPostTitle();

    String getContent();

    String getPostType();

    LocalDateTime getCreatedAt();

    String getProviderName();

    String getPictureUrl();
}
