package com.dlubera.grave.manager.service.domain.core.relative;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class Relative {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final LocalDate dateOfDeath;
    private final int age;
    private final byte[] image;
    private final String location;

    static Relative from(RelativeEntity relativeEntity) {
        return RelativeMapper.INSTANCE.toRelative(relativeEntity);
    }

    static Relative from(RelativeCreateRequestDto createRequestDto) {
        return RelativeMapper.INSTANCE.toRelative(createRequestDto);
    }
}