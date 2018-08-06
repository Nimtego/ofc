package com.example.pto6.ofc.dto;

public class DebitDTO {
    private String name;
    private String amount;
    private PaymentPeriod mPaymentPeriod;


    public DebitDTO(String name, String amount, PaymentPeriod paymentPeriod) {
        this.name = name;
        this.amount = amount;
        mPaymentPeriod = paymentPeriod;
    }

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
