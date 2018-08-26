package com.example.calculation.service;

import com.example.calculation.model.CalcUnit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface CalcService {
    Double amountPerSecond(List<CalcUnit> plus, List<CalcUnit> minus);

    BigDecimal amountToDate(List<CalcUnit> plus, List<CalcUnit> minus, LocalDateTime date);
}
