package com.dlubera.grave.manager.service.domain.core.user;

import com.dlubera.grave.manager.service.exception.ErrorDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(responseCode = "201", description = "New user created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    void registerUser(@RequestBody UserCreateRequestDto createRequestDto) {
        log.info("Request with new user registration received.");
        User user = User.from(createRequestDto);
        try {
            userService.createUser(user);
            log.info("New user with email={} registred", user.getEmail());
        } catch (Exception e) {
            throw e;
        }
    }

    //TODO remove responseStatus while will add other responses
    @GetMapping(path = "/{email}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Returns user by email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned successfully.")
    })
    UserDto getUser(@PathVariable String email) {
        try {
            return UserDto.from(userService.getUser(email));
        } catch (Exception e) {
            throw e;
        }
    }
}
