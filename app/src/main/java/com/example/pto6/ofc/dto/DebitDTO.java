package com.example.pto6.ofc.dto;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class DebitDTO extends UserFinanceDTO{
    private String name;
    private String amount;
    private PaymentPeriod mPaymentPeriod;
}
