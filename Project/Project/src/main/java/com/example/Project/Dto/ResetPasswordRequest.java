package com.example.Project.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResetPasswordRequest {
    private String email;
    private String newPassword;
    private String confirmNewPassword;
}
