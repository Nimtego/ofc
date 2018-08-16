package com.example.pto6.ofc.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Debit implements UserFinance {
    private String name;
    private long id;
    private Date createDate;
    private Date changeDate;
    private float arrival;
    private TypePeriod typePeriod;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public Date getChangeDate() {
        return changeDate;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setChangeDate(Date date) {
        this.changeDate = date;
    }

    @Override
    public void setCreateDate(Date date) {
        this.createDate = date;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }
}
