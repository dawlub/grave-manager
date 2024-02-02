package com.dlubera.grave.manager.service.visit.planner;

import com.dlubera.grave.manager.service.domain.core.relative.Relative;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@Schema(description = """
        Representation of visit for particular relative.
        """)
@RequiredArgsConstructor
class UserVisitDto {

    private final Relative relative;
    private final LocalDate localDate;
}
