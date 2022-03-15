package com.ferhad.unitech.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {
    @GetMapping
    public String test() {
        return "test";
    }
}
