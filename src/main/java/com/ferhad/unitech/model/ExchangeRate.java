package com.ferhad.unitech.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
@Table(name = "exchange_rate")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "exchange_rate_sequence", sequenceName = "exchangeRateSeq")
    private Long id;

    private Currency source;
    private Currency target;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal rate;
}
