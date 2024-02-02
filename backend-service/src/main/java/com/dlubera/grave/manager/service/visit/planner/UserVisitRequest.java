package com.dlubera.grave.manager.service.visit.planner;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@Builder
class UserVisitRequest {

    private final Authentication authentication;

    private final Long relativeId;

    private final LocalDate localDate;

}
