package com.ferhad.unitech.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class AccountGetDto {
    @NotBlank
    private String accountNumber;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;
}
