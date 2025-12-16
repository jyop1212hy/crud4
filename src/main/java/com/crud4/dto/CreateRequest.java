package com.crud4.dto;

public class CreateRequest {

    private String email;
    private String name;
    private String password;

    public CreateRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {return email;}
    public String getName() {return name;}
    public String getPassword() {return password;}
}
