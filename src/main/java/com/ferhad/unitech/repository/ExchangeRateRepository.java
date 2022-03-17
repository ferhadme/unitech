package com.ferhad.unitech.repository;

import com.ferhad.unitech.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Currency;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findBySourceIsAndTargetIs(Currency source, Currency target);
}