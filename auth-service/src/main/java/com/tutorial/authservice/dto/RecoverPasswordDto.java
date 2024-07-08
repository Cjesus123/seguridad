package com.tutorial.authservice.dto;

import lombok.Getter;

@Getter
public class RecoverPasswordDto {
    private String password;
    private String confirmPassword;
    private String tokenPassword;

    public RecoverPasswordDto(String password, String tokenPassword, String confirmPassword) {
        this.password = password;
        this.tokenPassword = tokenPassword;
        this.confirmPassword = confirmPassword;
    }

}
