package com.example.pto6.ofc.model;



import java.util.Date;

interface Credit {
    String name();
    long id();
    Date createDate();
    Date changeDate();
    Date dateOfCapture();
    int numberOfMonths();
    int percent();
    float arrivalSize();
    TypePeriod period();
}
