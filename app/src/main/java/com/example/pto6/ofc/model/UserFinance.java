package com.example.pto6.ofc.model;

import java.util.Date;

public interface UserFinance {
    String getName();

    long getId();

    Date getCreateDate();

    Date getChangeDate();

    void setId(long id);

    void setName(String name);

    void setChangeDate(Date date);
}
