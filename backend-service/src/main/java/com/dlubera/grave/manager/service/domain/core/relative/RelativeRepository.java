package com.dlubera.grave.manager.service.domain.core.relative;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


interface RelativeRepository extends JpaRepository<RelativeEntity, Long> {

    List<RelativeEntity> findAllByGraveId(Long graveId);

    Optional<RelativeEntity> findById(Long id);
}
