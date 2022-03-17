package com.ferhad.unitech.payload.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class SignupRequest {
    @NotBlank
    @Pattern(regexp = "^\\w{7}$",
            message = "Not a valid PIN")
    private String pin;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Password should contain minimum eight characters, at least one letter and one number")
    private String password;
}
