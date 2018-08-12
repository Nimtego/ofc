package com.example.pto6.ofc.model;

import java.util.Date;

public interface UserFinance extends Entity {
    String getName();

    Date getCreateDate();

    Date getChangeDate();

    void setName(String name);

    void setChangeDate(Date date);

    void setCreateDate(Date date);
}
