package com.example.pto6.ofc.model;

import com.example.repository.Entity;

import java.util.Date;

public interface UserFinance extends Entity {
    String getName();

    void setName(String name);

    Date getCreateDate();

    void setCreateDate(Date date);

    Date getChangeDate();

    void setChangeDate(Date date);
}
