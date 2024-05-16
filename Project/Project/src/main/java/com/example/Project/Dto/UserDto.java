package com.example.Project.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDto {

    public long id;

    private String username;

    private String email;

    private String password;

    private String role;

    private Boolean emailChecked;


    public UserDto(){}
    public UserDto( String username, String email, String password, String role, Boolean emailChecked) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.emailChecked = emailChecked;
    }
}
