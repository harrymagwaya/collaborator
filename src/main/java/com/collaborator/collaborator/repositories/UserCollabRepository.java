package com.collaborator.collaborator.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collaborator.collaborator.models.UserCollab;

public  interface UserCollabRepository extends JpaRepository<UserCollab, Long> {
    Optional <UserCollab> findByUserName(String userName);
}
