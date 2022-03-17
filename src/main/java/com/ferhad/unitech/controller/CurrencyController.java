package com.ferhad.unitech.controller;

import com.ferhad.unitech.dto.currency.CurrencyRateRequest;
import com.ferhad.unitech.dto.currency.CurrencyRateResponse;
import com.ferhad.unitech.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyRateService currencyRateService;

    @GetMapping
    public ResponseEntity<CurrencyRateResponse> getCurrencyRate(
            @RequestParam(name = "source") String source,
            @RequestParam(name = "target") String target
    ) {
        CurrencyRateRequest currencyRateRequest = new CurrencyRateRequest(source, target);
        return ResponseEntity.ok(
                currencyRateService.getCurrencyRate(currencyRateRequest)
        );
    }
}
