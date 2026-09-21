package com.afsaneh.square_users_api.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
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