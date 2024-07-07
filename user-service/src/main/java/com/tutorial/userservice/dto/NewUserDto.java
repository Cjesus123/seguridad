package com.tutorial.userservice.dto;

import com.tutorial.userservice.enums.RoleName;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
public class NewUserDto {
    private String name;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private RoleName role;

    public NewUserDto(){}

    public NewUserDto(String name,String lastName, String email, String userName, String password, RoleName role){
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
