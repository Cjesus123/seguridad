package com.tutorial.userservice.dto;

public class ChangePasswordDto {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public ChangePasswordDto(String oldPassword, String newPassword, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
