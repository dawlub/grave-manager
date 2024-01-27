package com.dlubera.grave.manager.service.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntitiesByEmail(String email);
}
