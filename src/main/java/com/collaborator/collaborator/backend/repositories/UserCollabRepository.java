package com.collaborator.collaborator.backend.repositories;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.collaborator.collaborator.backend.models.UserCollab;

@Repository
public  interface UserCollabRepository extends JpaRepository<UserCollab, Long> {
    Optional <UserCollab> findByEmail(String enail);

    Boolean existsByEmail(String email);

    UserCollab save(UserCollab user);
}
