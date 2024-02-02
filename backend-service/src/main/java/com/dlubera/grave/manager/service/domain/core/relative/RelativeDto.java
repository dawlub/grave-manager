package com.dlubera.grave.manager.service.domain.core.relative;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Schema(description = """
        Representation of one relative returned by the service
        """)
class RelativeDto {

    @Schema(example = "1")
    private final Long id;

    @Schema(example = "Krzysztof")
    private final String firstName;

    @Schema(example = "Krzysztof")
    private final String lastName;

    @Schema(example = "1950-02-23")
    private final LocalDate dateOfBirth;

    @Schema(example = "1999-02-23")
    private final LocalDate dateOfDeath;

    @Schema(example = "49")
    private final int age;

    @Schema(example = "long string")
    private final String imageBase64;

    @Schema(example = "Kolbuszowa")
    private final String location;

    static RelativeDto from(Relative relative) {
        return RelativeMapper.INSTANCE.toRelativeDto(relative);
    }
}
