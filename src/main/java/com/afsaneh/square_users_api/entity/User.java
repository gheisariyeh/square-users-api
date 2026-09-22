package com.afsaneh.square_users_api.entity;


import io.swagger.v3.oas.annotations.media.Schema;
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

    protected User() {
    }

    public User(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}