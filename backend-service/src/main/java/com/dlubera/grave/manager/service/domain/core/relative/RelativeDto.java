package com.dlubera.grave.manager.service.domain.core.relative;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
class RelativeDto {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final LocalDate dateOfDeath;
    private final int age;
    private final String imageBase64;
    private final String location;

    static RelativeDto from(Relative relative) {
        return RelativeMapper.INSTANCE.toRelativeDto(relative);
    }
}
