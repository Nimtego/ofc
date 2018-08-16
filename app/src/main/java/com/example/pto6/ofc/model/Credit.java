package com.example.pto6.ofc.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Credit implements UserFinance {
    private String name;
    private long id;
    private Date createDate;
    private Date changeDate;
    private Date dateOfCapture;
    private int numberOfMonths;
    private int percent;
    private float arrivalSize;
    private CreditType creditType;
}
