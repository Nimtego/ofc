package com.example.pto6.ofc.model;



public class BaseDebit extends AbstractUserDebit {

    public BaseDebit(GeneralData generalData) {
        super(generalData);
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
