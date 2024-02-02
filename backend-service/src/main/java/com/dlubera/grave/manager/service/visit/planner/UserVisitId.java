package com.dlubera.grave.manager.service.visit.planner;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserVisitId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "dead_id")
    private Long deadId;
}
