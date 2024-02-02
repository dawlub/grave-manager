package com.dlubera.grave.manager.service.visit.planner;

import com.dlubera.grave.manager.service.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "User visit management", description = "Api allows to manage user visits for particular relatives.")
@Slf4j
@RestController()
@RequestMapping(value = "v1/visit/relative", produces = "application/json")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000", methods = RequestMethod.POST)
class UserVisitController {

    private final UserVisitService userVisitService;

    @PostMapping(path = "/{relativeId}/planned-date/{date}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates relative visit for user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New visit created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    UserVisitDto registerUserVisit(@PathVariable Long relativeId,
                           @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                           Authentication authentication) {
        log.info("Request with new visit received.");
        var request = UserVisitRequest.builder()
                .authentication(authentication)
                .relativeId(relativeId)
                .localDate(date)
                .build();
        try {
            return userVisitService.createNewRelativeVisit(request);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping (path = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Retrieves all visits of a given user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User visits fetched successfully.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema (schema = @Schema(implementation = UserVisitDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    List<UserVisitDto> getAllUserVisits(Authentication authentication) {
        try {
            return userVisitService.getAllUserVisits(authentication);
        } catch (Exception e) {
            throw e;
        }
    }
}
