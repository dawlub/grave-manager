package com.dlubera.grave.manager.service.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("v1/user")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    @PostMapping(path = "/registration", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates new service user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New user created successfully.")
    })
    void registerUser(@RequestBody UserCreateRequestDto createRequestDto) {
        User user = User.from(createRequestDto);
        try {
            userService.createUser(user);
        } catch (Exception e) {
            throw e;
        }
    }

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
