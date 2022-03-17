package com.ferhad.unitech.service;

import com.ferhad.unitech.dto.currency.CurrencyRateRequest;
import com.ferhad.unitech.dto.currency.CurrencyRateResponse;

public interface CurrencyRateService {
    CurrencyRateResponse getCurrencyRate(CurrencyRateRequest currencyRateRequest);
}
