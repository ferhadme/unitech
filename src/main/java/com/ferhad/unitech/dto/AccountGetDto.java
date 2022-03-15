package com.ferhad.unitech.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountGetDto {
    private String accountNumber;
    private BigDecimal amount;
    private Boolean active;
}
