package com.example.pto6.ofc.model;

import android.widget.Button;

import java.util.Date;

public class BaseCredit extends BaseUserFinance implements Credit {

    private Date dateOfCapture;
    private int numberOfMonths;
    private int percent;
    private float arrivalSize;
    private CreditType creditType;


    private BaseCredit(String name) {
        super(name);
    }

    @Override
    public Date dateOfCapture() {
        return null;
    }

    @Override
    public int numberOfMonths() {
        return 0;
    }

    @Override
    public int percent() {
        return 0;
    }

    @Override
    public float arrivalSize() {
        return 0;
    }

    @Override
    public CreditType creditType() {
        return creditType;
    }

    public static Builder newBuilder(String name) {
        return new BaseCredit(name).new Builder();
    }
    public class Builder {
        private Builder(){}
        
        public Builder dateOfCapture(Date data) {
            BaseCredit.this.dateOfCapture = data;

            return this;
        }

        public Builder numberOfMonths(int months) {
            BaseCredit.this.numberOfMonths = months;

            return this;
        }

        public Builder percent(int percent) {
            BaseCredit.this.percent = percent;

            return this;
        }

        public Builder arrival(float arrivalSize) {
            BaseCredit.this.arrivalSize = arrivalSize;

            return this;
        }

        public Builder creditType(CreditType creditType) {
            BaseCredit.this.creditType = creditType;

            return this;
        }
        public BaseCredit build() {
            BaseCredit bc = new BaseCredit(name());
            bc.dateOfCapture = BaseCredit.this.dateOfCapture;
            bc.creditType = BaseCredit.this.creditType;
            bc.arrivalSize = BaseCredit.this.arrivalSize;
            bc.numberOfMonths = BaseCredit.this.numberOfMonths;
            bc.percent = BaseCredit.this.percent;
            return BaseCredit.this;
        }
    }
}
