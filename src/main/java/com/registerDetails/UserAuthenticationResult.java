package com.registerDetails;

public class UserAuthenticationResult {
    private String role;
    private String username;

    // Constructors, getters, and setters

    // Constructor with both values
    public UserAuthenticationResult(String role, String username) {
        this.role = role;
        this.username = username;
    }

    // Getters and setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
