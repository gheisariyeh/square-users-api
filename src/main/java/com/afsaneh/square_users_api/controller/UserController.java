package com.afsaneh.square_users_api.controller;

import com.afsaneh.square_users_api.entity.User;
import com.afsaneh.square_users_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(
        name = "Users",
        description = "Operations related to users"
)
@RestController
@RequestMapping ("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Create a user",
            description = "Creates a new user with a generated UUID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User created successfully"
    )
    @PostMapping
    public User createUser() {
        return userService.createUser();
    }

    @Operation(
            summary = "Get a user",
            description = "Returns a user identified by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User lookup completed"
    )
    @GetMapping("/{id}")
    public Optional<User> getUser(
            @Parameter(
                    description = "Identifier of the user",
                    required = true
            )
            @PathVariable String id) {
        return userService.findById(id);
    }

    @Operation(
            summary = "Delete a user",
            description = "Deletes a user identified by its ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Delete request completed"
    )
    @DeleteMapping("/{id}")
    public void deleteUser(
            @Parameter(
                    description = "Identifier of the user to delete",
                    required = true
            )
            @PathVariable String id) {
        userService.deleteById(id);
    }

    @Operation(
            summary = "Check if a user is valid",
            description = "Returns true if the user exists, otherwise false"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User validity returned successfully"
    )
    @GetMapping("/{id}/valid")
    public boolean isValidUser(
            @Parameter(
                    description = "Identifier of the user",
                    required = true
            )
            @PathVariable String id) {
        return userService.isValidUser(id);
    }

}
