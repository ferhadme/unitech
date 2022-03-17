package com.ferhad.unitech.dto.currency;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyRateRequest {
    private String sourceCurrency;
    private String targetCurrency;
}
