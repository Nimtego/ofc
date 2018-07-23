package com.example.pto6.ofc.model;



import java.util.Date;

public abstract class AbstractUserCredit implements Credit {
    private long id;
    private String name;
    private Date createDate;
    private Date changeDate;

    @Override
    public String name() {
        return name;
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public Date createDate() {
        return createDate;
    }

    @Override
    public Date changeDate() {
        return changeDate;
    }
}
