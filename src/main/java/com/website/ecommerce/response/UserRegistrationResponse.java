package com.website.ecommerce.response;

public class UserRegistrationResponse {
    private String message;
    private Long id;
    private String username;
    private String password;

    public UserRegistrationResponse() {
    }

    public UserRegistrationResponse(String message, Long id, String username, String password) {
        this.message = message;
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
