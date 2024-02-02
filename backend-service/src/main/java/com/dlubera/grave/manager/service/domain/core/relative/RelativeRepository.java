package com.dlubera.grave.manager.service.domain.core.relative;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


interface RelativeRepository extends JpaRepository<RelativeEntity, Long> {

    List<RelativeEntity> findAllByGraveId(Long graveId);
}
