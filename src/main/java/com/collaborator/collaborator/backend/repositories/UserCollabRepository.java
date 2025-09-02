package com.collaborator.collaborator.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collaborator.collaborator.backend.models.UserCollab;

public  interface UserCollabRepository extends JpaRepository<UserCollab, Long> {
    Optional <UserCollab> findByUserName(String userName);
}
