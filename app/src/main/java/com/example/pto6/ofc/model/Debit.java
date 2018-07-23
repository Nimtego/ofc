package com.example.pto6.ofc.model;



import java.util.Date;

interface Debit {
    String name();
    long id();
    Date createDate();
    Date changeDate();
    float arrivalSize();
    TypePeriod period();
}