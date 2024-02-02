package com.dlubera.grave.manager.service.domain.core.relative;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("v1/relative")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "http://localhost:3000/**")
class RelativeController {

    private final RelativeServiceImpl relativeService;

    @PostMapping(path = "/create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates new relative user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New relative created successfully.")
    })
    RelativeDto createRelative(@RequestBody RelativeCreateRequestDto createRequestDto) {
        try {
            var relative = Relative.from(createRequestDto);
            return RelativeDto.from(relativeService.saveNewRelative(relative));
        } catch (Exception e) {
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
