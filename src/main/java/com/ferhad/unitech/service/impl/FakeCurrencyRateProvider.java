package com.ferhad.unitech.service.impl;

import com.ferhad.unitech.model.ExchangeRate;
import com.ferhad.unitech.repository.ExchangeRateRepository;
import com.ferhad.unitech.service.CurrencyRateProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

@Service
@RequiredArgsConstructor
public class FakeCurrencyRateProvider implements CurrencyRateProvider {
    private final ExchangeRateRepository exchangeRateRepository;

    @Scheduled(cron = "0 * * * * *")
    @Override
    public void reloadData() {
        Currency azn = Currency.getInstance("AZN");
        Currency usd = Currency.getInstance("USD");
        Currency tl = Currency.getInstance("TRY");

        ExchangeRate aznToUsd = generateExchangeRate(azn, usd, 0.58, 0.59);
        exchangeRateRepository.save(aznToUsd);
        ExchangeRate aznToTl = generateExchangeRate(azn, tl, 6.0, 9.0);
        exchangeRateRepository.save(aznToTl);

        ExchangeRate usdToAzn = generateExchangeRate(usd, azn, 1.69, 1.7);
        exchangeRateRepository.save(usdToAzn);
        ExchangeRate usdToTl = generateExchangeRate(usd, tl, 10.0, 14.0);
        exchangeRateRepository.save(usdToTl);

        ExchangeRate tlToUsd = generateExchangeRate(tl, usd, 0.08, 0.13);
        exchangeRateRepository.save(tlToUsd);
        ExchangeRate tlToAzn = generateExchangeRate(tl, azn, 0.1, 0.2);
        exchangeRateRepository.save(tlToAzn);
    }

    private ExchangeRate generateExchangeRate(Currency source, Currency target, Double min, Double max) {
        ExchangeRate rate = exchangeRateRepository.findBySourceIsAndTargetIs(source, target).orElse(
                ExchangeRate.builder()
                        .source(source)
                        .target(target)
                        .build()
        );
        rate.setRate(generateRandomBigDecimalFromRange(min, max));
        return rate;
    }

    public static BigDecimal generateRandomBigDecimalFromRange(Double minD, Double maxD) {
        BigDecimal min = BigDecimal.valueOf(minD);
        BigDecimal max = BigDecimal.valueOf(maxD);
        return min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
    }
}
