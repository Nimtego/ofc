package com.example.pto6.ofc.model;

import java.util.Date;

public abstract class AbstractUserFinance implements UserFinance {

    private GeneralData generalData;

    public AbstractUserFinance(GeneralData generalData) {
        this.generalData = generalData;
    }

    @Override
    public String name() {
        return generalData.name();
    }

    @Override
    public long id() {
        return generalData.id();
    }

    @Override
    public Date createDate() {
        return generalData.createDate();
    }

    @Override
    public Date changeDate() {
        return generalData.changeDate();
    }

}
