package com.example.pto6.ofc.model;


import java.util.Date;

public class BaseCredit extends AbstractUserFinance implements Credit{

    private Date dateOfCapture;
    private int numberOfMonths;
    private int percent;
    private float arrivalSize;

    public BaseCredit(GeneralData generalData) {
        super(generalData);
    }

    @Override
    public Date dateOfCapture() {
        return dateOfCapture;
    }

    @Override
    public int numberOfMonths() {
        return numberOfMonths;
    }

    @Override
    public int percent() {
        return percent;
    }

    @Override
    public float arrivalSize() {
        return 0;
    }

    @Override
    public TypePeriod period() {
        return null;
    }
}
