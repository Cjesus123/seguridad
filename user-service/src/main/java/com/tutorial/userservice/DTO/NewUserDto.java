package com.tutorial.userservice.DTO;

import com.tutorial.userservice.entity.Role;
import com.tutorial.userservice.enums.RoleName;
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
    private RoleName role;
}
