package com.tutorial.userservice.dto;

import com.tutorial.userservice.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateUserDto {
    private String name;
    private String lastName;
    private String email;
    private String userName;
    private RoleName role;
}
