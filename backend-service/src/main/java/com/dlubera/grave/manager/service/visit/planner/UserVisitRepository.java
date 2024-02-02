package com.dlubera.grave.manager.service.visit.planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserVisitRepository extends JpaRepository<UserVisitEntity, UserVisitId> {

    List<UserVisitEntity> findAllByUserVisitIdUserId(@Param("userId") Long userId);
}
