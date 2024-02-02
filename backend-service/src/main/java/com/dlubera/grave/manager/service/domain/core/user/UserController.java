package com.dlubera.grave.manager.service.domain.core.user;

import com.dlubera.grave.manager.service.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User management API", description = "Api allows to manage user data and store it in db.")
@Slf4j
@RestController()
@RequestMapping(value = "v1/user")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000", methods = RequestMethod.POST)
class UserController {

    private final UserServiceImpl userService;

    @PostMapping(path = "/registration", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates new service user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New user created successfully.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    UserDto registerUser(@RequestBody UserCreateRequestDto createRequestDto) {
        log.info("Request with new user registration received.");
        User user = User.from(createRequestDto);
        try {
            return UserDto.from(userService.createUser(user));
        } catch (Exception e) {
            throw e;
        }
    }

    //TODO remove responseStatus while will add other responses
    @GetMapping(path = "/{email}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Returns user by email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned successfully.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request exception.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDetails.class))),
    })
    UserDto getUser(@PathVariable String email) {
        try {
            return UserDto.from(userService.getUser(email));
        } catch (Exception e) {
            throw e;
        }
    }
}
