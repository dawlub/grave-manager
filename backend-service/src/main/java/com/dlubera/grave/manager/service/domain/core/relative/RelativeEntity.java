package com.dlubera.grave.manager.service.domain.core.relative;

import jakarta.persistence.*;
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
@Table(name = "dead")
class RelativeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "death_date")
    private LocalDate dateOfDeath;

    @Column(name = "birth_date")
    private LocalDate dateOfBirth;

    @Column(name = "age")
    private int age;

    @Column(name = "grave_id")
    private Long graveId;

    @Column(name= "location")
    private String location;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "created_at")
    @UpdateTimestamp
    private ZonedDateTime created_at;

    static RelativeEntity from(Relative relative) {
        return RelativeMapper.INSTANCE.toRelativeEntity(relative);
    }
}
