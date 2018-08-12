package com.example.pto6.ofc.model;

public enum CreditType {
    ANNUITY {
        @Override
        public String getName() {
            return "annuity";
        }
    }, DIFFERENTIATED {
        @Override
        public String getName() {
            return "differentiated";
        }
    };

    public abstract String getName();
}
