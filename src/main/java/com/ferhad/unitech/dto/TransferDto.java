package com.ferhad.unitech.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class TransferDto {
    @DecimalMin(value = "0.0", inclusive = false)
    private final BigDecimal amount;

    @NotBlank
    private final String accountNumberFrom;

    @NotBlank
    private final String accountNumberTo;
}
