package com.collaborator.collaborator.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.collaborator.collaborator.backend.dto.PostWithAuthorDTO;
import com.collaborator.collaborator.backend.dto.PostWithAuthorInfo;
import com.collaborator.collaborator.backend.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query("SELECT p FROM Post p JOIN FETCH p.userCollab WHERE p.id = :id")
//   Optional<Post> findByIdWithUser(@Param("id") Long id);

//     @Query("""
//     SELECT p.postId AS postId,
//            p.postTitle AS postTitle,
//            p.content AS content,
//            p.postType AS postType,
//            p.createdAt AS createdAt,
//            sp.providerName AS providerName,
//            sp.pictureUrl AS pictureUrl
//     FROM ServicePost p
//     JOIN p.author sp
// """)
// List<PostWithAuthorInfo> findAllPostsWithAuthorInfo();

@Query(value = """
        SELECT
            p.post_id AS postId,
            p.post_title AS postTitle,
            p.content AS content,
            p.post_type AS postType,
            p.created_at AS createdAt,
            sp.provider_name AS providerName,
            sp.picture_url AS pictureUrl
        FROM
            service_posts p
        JOIN
            service_provider sp ON p.author_id = sp.provider_id
        """, nativeQuery = true)
List<PostWithAuthorDTO> findAllPostWithAuthor();
 
}
