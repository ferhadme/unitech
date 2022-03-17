package com.ferhad.unitech.dto.currency;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyRateResponse {
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal rate;
}
