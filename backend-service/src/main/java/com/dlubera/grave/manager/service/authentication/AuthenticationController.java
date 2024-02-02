package com.dlubera.grave.manager.service.authentication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController()
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000", methods = RequestMethod.POST)
class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("v1/auth/signup")
    AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        log.info("Authorization request received for user with email=#{}", authenticationRequest.getEmail());
        try {
            return authenticationService.signIn(authenticationRequest);
        } catch (Exception e) {
            log.info("Can't authorize user with email=#{}", authenticationRequest.getEmail());
            throw e;
        }
    }
}
