package com.afsaneh.square_users_api.controller;

import com.afsaneh.square_users_api.dto.LoginRequest;
import com.afsaneh.square_users_api.entity.User;
import com.afsaneh.square_users_api.security.JwtService;
import com.afsaneh.square_users_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request
    ) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.username(),
                                    request.password()
                            )
                    );
            User user = userService.findByUsername(request.username())
                    .orElseThrow();
            String role = authentication.getAuthorities().stream().findFirst().orElseThrow().getAuthority();

            String token = jwtService.generateToken(user.getId(), user.getUsername(), role);

            return ResponseEntity.ok(token);

        } catch (AuthenticationException exception) {

            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}