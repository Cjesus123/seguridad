package com.tutorial.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NewUserDto {
    private String name;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private String tokenPassword;
    private String role;
}
