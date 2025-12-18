package com.crud4.user.dto.request;

public class UpdateRequest {

    private final String email;
    private final String name;

    public UpdateRequest(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {return email;}
    public String getName() {return name;}
}
