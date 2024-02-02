package com.dlubera.grave.manager.service.visit.planner;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visit")
class UserVisitEntity {

    @EmbeddedId
    private UserVisitId userVisitId;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "created_at")
    @UpdateTimestamp
    private ZonedDateTime created_at;
}
