package com.example.pto6.ofc.model;

import java.util.Date;

public abstract class AbstractUserDebit implements Debit{

    private GeneralData generalData;

    public AbstractUserDebit(GeneralData generalData) {
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
