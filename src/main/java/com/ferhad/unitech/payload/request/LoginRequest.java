package com.ferhad.unitech.payload.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class LoginRequest {
    @NotBlank(message = "PIN code is mandatory")
    private String pin;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
