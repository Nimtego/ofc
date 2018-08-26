package com.example.calculation.service;

import com.example.calculation.model.CalcUnit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestCalcUnit implements CalcUnit {
    private LocalDateTime from;
    private LocalDateTime to;
    private BigDecimal amount;
    private Period period;
}
