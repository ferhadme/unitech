package com.ferhad.unitech.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDto {
    private final BigDecimal amount;
    private final String accountNumberFrom;
    private final String accountNumberTo;
}
