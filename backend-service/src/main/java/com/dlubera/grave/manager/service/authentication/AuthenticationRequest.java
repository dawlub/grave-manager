package com.dlubera.grave.manager.service.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
class AuthenticationRequest {

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
}
