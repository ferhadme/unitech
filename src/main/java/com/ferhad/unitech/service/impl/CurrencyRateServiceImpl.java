package com.ferhad.unitech.service.impl;

import com.ferhad.unitech.dto.currency.CurrencyRateRequest;
import com.ferhad.unitech.dto.currency.CurrencyRateResponse;
import com.ferhad.unitech.exception.NoSuchCurrencyCode;
import com.ferhad.unitech.model.ExchangeRate;
import com.ferhad.unitech.repository.ExchangeRateRepository;
import com.ferhad.unitech.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {
    private final ExchangeRateRepository exchangeRateRepository;

    @Cacheable("rates")
    @Override
    public CurrencyRateResponse getCurrencyRate(CurrencyRateRequest currencyRateRequest) {
        try {
            ExchangeRate exchangeRate = exchangeRateRepository.findBySourceIsAndTargetIs(
                    Currency.getInstance(currencyRateRequest.getSourceCurrency()),
                    Currency.getInstance(currencyRateRequest.getTargetCurrency())
            ).orElseThrow(NoSuchCurrencyCode::new);

            return new CurrencyRateResponse(
                    exchangeRate.getSource().getCurrencyCode(),
                    exchangeRate.getTarget().getCurrencyCode(),
                    exchangeRate.getRate()
            );
        } catch (IllegalArgumentException ex) {
            throw new NoSuchCurrencyCode();
        }
    }

}
