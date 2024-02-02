package com.dlubera.grave.manager.service.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class ErrorDetails {
    @Schema(name = "error name")
    private final String name;

    @Schema(name = "error key")
    private final String key;

    @Schema(name = "error message")
    private final String message;
}
