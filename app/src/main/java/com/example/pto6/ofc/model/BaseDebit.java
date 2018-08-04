package com.example.pto6.ofc.model;

public class BaseDebit extends BaseUserFinance implements Debit {

    private float arrival;
    private TypePeriod typePeriod;

    public BaseDebit(String name, float arrival, TypePeriod typePeriod) {
        super(name);
        this.arrival = arrival;
        this.typePeriod = typePeriod;
    }

    @Override
    public float arrivalSize() {
        return arrival;
    }

    @Override
    public TypePeriod period() {
        return typePeriod;
    }
}
