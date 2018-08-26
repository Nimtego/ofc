package com.example.calculation.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;

public interface CalcUnit {
    BigDecimal getAmount();

    Period getPeriod();

    LocalDateTime getFrom();

    LocalDateTime getTo();
}
