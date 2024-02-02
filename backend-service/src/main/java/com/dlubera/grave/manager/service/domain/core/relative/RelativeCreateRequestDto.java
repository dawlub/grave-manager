package com.dlubera.grave.manager.service.domain.core.relative;

import com.dlubera.grave.manager.service.exception.GraveManagerServiceException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Schema(description = """
        Schema responsible of handling new relative creation operation.
        Contains all required information which must be provided to create new relative in db.
        Remember that the date of birth should be less than the date of death.
        """)
class RelativeCreateRequestDto {

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "Krzysztof")
    private final String firstName;

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "Nowak")
    private final String lastName;

    @NotBlank
    @Schema(example = "1951-01-01")
    private final LocalDate dateOfBirth;

    @NotBlank
    @Schema(example = "2011-02-02")
    private final LocalDate dateOfDeath;

    @NotBlank
    @Schema(example = "File with relative image")
    private final MultipartFile image;

    @NotBlank
    @Schema(example = "Kolbuszowa")
    private final String location;

    public RelativeCreateRequestDto(String firstName, String lastName, LocalDate dateOfBirth,
                                    LocalDate dateOfDeath, MultipartFile image, String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.image = image;
        this.location = location;
        validate(this);
    }

    private void validate(RelativeCreateRequestDto relativeCreateRequestDto) {
        var birthDate = relativeCreateRequestDto.getDateOfBirth();
        var deathDate = relativeCreateRequestDto.getDateOfDeath();
        if(deathDate.isBefore(birthDate)) {
            throw new GraveManagerServiceException("You can't die before you born.");
        }
    }
}
