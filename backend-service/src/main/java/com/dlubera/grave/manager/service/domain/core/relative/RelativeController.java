package com.dlubera.grave.manager.service.domain.core.relative;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("v1/relative")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.GET})
class RelativeController {

    private final RelativeServiceImpl relativeService;

    @PostMapping(path = "/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates new relative user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New relative created successfully.")
    })
    RelativeDto createRelative(@ModelAttribute RelativeCreateRequestDto createRequestDto) {
        log.info("Request to persist new relative/dead with firstName={} and lastName={} received",
                createRequestDto.getFirstName(), createRequestDto.getLastName());
        try {
            var relative = Relative.from(createRequestDto);
            return RelativeDto.from(relativeService.saveNewRelative(relative));
        } catch (Exception e) {
            final var message = "Failed to persist new relative/dead with firstName=%s and lastName=%s".formatted(
                    createRequestDto.getFirstName(), createRequestDto.getLastName());
            log.error(message, e);
            throw e;
        }
    }

    @GetMapping(path = "/all")
    @Operation(description = "Returns all the dead people from db.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns all the dead people stored in db.")
    })
    List<RelativeDto> getAllFromCemetery() {
        try {
            return relativeService.getAllRelatives().stream()
                    .map(RelativeDto::from)
                    .toList();
        } catch (Exception e) {
            throw e;
        }
    }
}
