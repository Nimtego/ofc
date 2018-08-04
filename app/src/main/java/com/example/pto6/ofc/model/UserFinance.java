package com.example.pto6.ofc.model;

import java.util.Date;

public interface UserFinance {
    String name();

    long id();

    Date createDate();

    Date changeDate();

    void setId(long id);

    void setName(String name);

    void setChangeDate(Date date);
}
