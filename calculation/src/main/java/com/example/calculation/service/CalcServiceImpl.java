package com.example.calculation.service;

import com.example.calculation.model.CalcUnit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CalcServiceImpl implements CalcService {
    @Override
    public Double amountPerSecond(List<CalcUnit> plus, List<CalcUnit> minus) {
        return getAmountPerSecond(plus) - getAmountPerSecond(minus);
    }

    @Override
    public BigDecimal amountToDate(List<CalcUnit> plus, List<CalcUnit> minus, LocalDateTime date) {
        return getAmountToDate(plus, date).subtract(getAmountToDate(minus, date));
    }

    BigDecimal getAmountToDate(List<CalcUnit> units, LocalDateTime date) {
        return units.stream()
                .map(unit -> getAmountToDate(unit, date))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    BigDecimal getAmountToDate(CalcUnit unit, LocalDateTime date) {
        LocalDateTime to = unit.getTo().isAfter(date) ? date : unit.getTo();
        long seconds = getSecondsBetween(unit.getFrom(), to);
        return BigDecimal.valueOf(getAmountPerSecond(unit)).multiply(BigDecimal.valueOf(seconds));
    }

    long getSecondsBetween(LocalDateTime before, LocalDateTime after) {
        return ChronoUnit.SECONDS.between(before, after);
    }

    double getAmountPerSecond(List<CalcUnit> units) {
        return units.stream()
                .map(this::getAmountPerSecond)
                .reduce(0.0, (p, n) -> p + n);
    }

    double getAmountPerSecond(CalcUnit unit) {
        LocalDateTime now = LocalDateTime.now();
        Period period = unit.getPeriod();
        LocalDateTime after = now.plus(period);
        long seconds = now.until(after, ChronoUnit.SECONDS);
        return unit.getAmount().doubleValue() / seconds;
    }
}
