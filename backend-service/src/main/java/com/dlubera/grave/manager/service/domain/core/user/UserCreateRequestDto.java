package com.dlubera.grave.manager.service.domain.core.user;


import com.dlubera.grave.manager.service.exception.GraveManagerServiceException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Schema(description = """
        Schema responsible of handling new user creation operation.
        Contains all required information which must be provided to create and save new user in the system.
        """)
class UserCreateRequestDto {

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "Jan")
    private final String firstName;

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "Kowalski")
    private final String lastName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @Size(max = 48)
    @Schema(example = "kowalski99@gmail.com")
    private final String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    @Size(max = 120)
    @Schema(example = "Password1$")
    private final String password;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    @Size(max = 120)
    @Schema(example = "Password1$")
    private final String passwordConfirmation;

    UserCreateRequestDto(String firstName, String lastName, String email, String password, String passwordConfirmation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        validate(this);
    }

    private static void validate(UserCreateRequestDto dto) {
        if (!dto.getPassword().equals(dto.passwordConfirmation)) {
            throw new GraveManagerServiceException("Provided fields password and passwordConfirmation are not same.");
        }
    }
}
