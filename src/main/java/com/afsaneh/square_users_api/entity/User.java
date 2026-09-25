package com.afsaneh.square_users_api.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Schema(description = "Application user")
public class User {
    @Id
    @Schema(
            description = "Unique identifier of the user",
            example = "e522d61b-8f0c-4556-976a-a3837b9ccb74"
    )
    private String id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;

    protected User() {
    }

    public User(String id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public String getRole() {
        return role;
    }
}