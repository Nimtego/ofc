package com.example.calculation.service;

import com.example.calculation.model.CalcUnit;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class CalcServiceImplTest {

    private CalcServiceImpl service = new CalcServiceImpl();

    @Test
    public void amountPerSecondTest() {
        List<CalcUnit> plus = getPlus();
        List<CalcUnit> minus = getMinus();
        Double salary = 50_000D / (30 * 24 * 60 * 60);
        Double bonus = 50_000D / (366 * 24 * 60 * 60);
        Double lunch = 250D * 5 / (7 * 24 * 60 * 60);
        Double rent = 5_000D / (30 * 24 * 60 * 60);
        Double tax = 10_000D / (365 * 24 * 60 * 60);
        Double expected = salary + bonus - lunch - rent - tax;
        Double actual = service.amountPerSecond(plus, minus);
        log.debug("expected: [{}], actual: [{}]", expected, actual);
        assertEquals(expected, actual, 0.1);
    }

    @Test
    public void getAmountPerSecondTest() {
        CalcUnit unit = getUnit(BigDecimal.valueOf(10_000), Period.ofDays(5));
        double actual = service.getAmountPerSecond(unit);
        double expected = 10_000d / (5 * 24 * 60 * 60);
        log.debug("expected: [{}], actual: [{}]", expected, actual);
        assertEquals(expected, actual, 0.0000001);
    }

    @Test
    public void amountToDateTest() {
        List<CalcUnit> plus = Stream.of(
                TestCalcUnit.builder()
                        .amount(BigDecimal.valueOf(50_000))
                        .period(Period.ofMonths(1))
                        .from(LocalDateTime.now())
                        .to(LocalDateTime.MAX)
                        .build()
        ).collect(Collectors.toList());
        List<CalcUnit> minus = Stream.of(
                TestCalcUnit.builder()
                        .amount(BigDecimal.valueOf(10_000))
                        .period(Period.ofMonths(1))
                        .from(LocalDateTime.now())
                        .to(LocalDateTime.now().plus(Period.ofMonths(3)))
                        .build(),
                TestCalcUnit.builder()
                        .amount(BigDecimal.valueOf(5_000))
                        .period(Period.ofMonths(1))
                        .from(LocalDateTime.now())
                        .to(LocalDateTime.MAX)
                        .build()
        ).collect(Collectors.toList());
        int testPeriod = 8; // months
        Double salary = 50_000D / (30 * 24 * 60 * 60);
        Double rent = 5_000D / (30 * 24 * 60 * 60);
        Double credit = 10_000D / (30 * 24 * 60 * 60);
        BigDecimal totalSalary = BigDecimal.valueOf(salary)
                .multiply(BigDecimal.valueOf(4 * 30 * 24 * 60 * 60 + 4 * 31 * 24 * 60 * 60));
        BigDecimal totalRent = BigDecimal.valueOf(rent)
                .multiply(BigDecimal.valueOf(4 * 30 * 24 * 60 * 60 + 4 * 31 * 24 * 60 * 60));
        BigDecimal totalCredit = BigDecimal.valueOf(credit)
                .multiply(BigDecimal.valueOf(2 * 30 * 24 * 60 * 60 + 31 * 24 * 60 * 60));
        BigDecimal expected = totalSalary.subtract(totalCredit).subtract(totalRent);
        BigDecimal actual = service
                .amountToDate(plus, minus, LocalDateTime.now().plus(Period.ofMonths(testPeriod)));
        log.debug("expected: [{}], actual: [{}]", expected, actual);
        assertTrue(Math.abs(expected.compareTo(actual)) < (4 * 30 + 4 * 31) * 60 * 60 / 10);
    }

    @Test
    public void getAmountToDateTest() {
        CalcUnit unit = getUnit(BigDecimal.valueOf(10_000), Period.ofDays(5));
        BigDecimal expected = BigDecimal.valueOf(10_000)
                .multiply(BigDecimal.valueOf(333))
                .divide(BigDecimal.valueOf(5), BigDecimal.ROUND_HALF_EVEN);
        BigDecimal actual = service.getAmountToDate(unit, LocalDateTime.now().plus(Period.ofDays(333)));
        log.debug("expected: [{}], actual: [{}]", expected, actual);
        assertTrue(Math.abs(expected.compareTo(actual)) < 333 / 10);
    }

    private List<CalcUnit> getPlus() {
        return Stream.of(
                getUnit(BigDecimal.valueOf(50_000), Period.ofMonths(1)), // salary
                getUnit(BigDecimal.valueOf(50_000), Period.ofYears(1))   // annual bonus
        )
                .collect(Collectors.toList());
    }

    private List<CalcUnit> getMinus() {
        return Stream.of(
                getUnit(BigDecimal.valueOf(250)
                                .multiply(BigDecimal.valueOf(5)),
                        Period.ofWeeks(1)),                              // lunches
                getUnit(BigDecimal.valueOf(5_000), Period.ofMonths(1)),  // rent
                getUnit(BigDecimal.valueOf(10_000), Period.ofYears(1))   // tax
        )
                .collect(Collectors.toList());
    }

    private CalcUnit getUnit(BigDecimal amount, Period period) {
        return TestCalcUnit.builder()
                .amount(amount)
                .period(period)
                .from(LocalDateTime.now())
                .to(LocalDateTime.now().plus(Period.ofYears(5)))
                .build();
    }
}