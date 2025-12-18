package com.crud4.user.dto.request;

public class CreateMemberRequest {

    private String email;
    private String name;
    private String password;

    public CreateMemberRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {return email;}
    public String getName() {return name;}
    public String getPassword() {return password;}
}
