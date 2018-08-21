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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PaymentPeriod getPaymentPeriod() {
        return mPaymentPeriod;
    }

    public void setPaymentPeriod(PaymentPeriod paymentPeriod) {
        mPaymentPeriod = paymentPeriod;
    }
}
