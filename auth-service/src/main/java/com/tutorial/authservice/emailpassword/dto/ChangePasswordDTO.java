package com.tutorial.authservice.emailpassword.dto;


import jakarta.validation.constraints.NotBlank;

public class ChangePasswordDTO {
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String tokenPassword;

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(String password, String confirmPassword, String tokenPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.tokenPassword = tokenPassword;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public @NotBlank String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotBlank String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public @NotBlank String getTokenPassword() {
        return tokenPassword;
    }

    public void setTokenPassword(@NotBlank String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }
}


