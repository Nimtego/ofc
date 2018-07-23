package com.example.pto6.ofc.model;

import java.util.Date;

public abstract class BaseUserFinance implements UserFinance {

    private String name;
    private long id;
    private Date create;
    private Date change;

    public BaseUserFinance(String name) {
        this.name = name;
        create = new Date();
        change = create;
    }

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
        return create;
    }

    @Override
    public Date changeDate() {
        return change;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setChangeDate(Date date) {
        this.change = date;
    }
}
