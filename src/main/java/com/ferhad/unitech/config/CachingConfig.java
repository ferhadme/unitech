package com.ferhad.unitech.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CachingConfig {
    private final CacheManager cacheManager;

    @Scheduled(cron = "0 * * * * *")
    public void clearCurrencyRateCache() {
        Cache ratesCache = cacheManager.getCache("rates");
        if (ratesCache != null) {
            ratesCache.clear();
        }
    }
}
