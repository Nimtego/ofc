package com.example.pto6.ofc.model;

import java.util.ArrayList;
import java.util.List;

public final class StubDBHelper implements DBHelper {

    private List<Debit> debitList;
    private List<Credit> creditList;
    private static volatile StubDBHelper instance;

    private StubDBHelper() {
        this.debitList = new ArrayList<>();
        this.creditList = new ArrayList<>();
       /* debitList.add(new BaseDebit("Test", 1000f, TypePeriod.DAY));
        debitList.add(new BaseDebit("Test2", 1000f, TypePeriod.DAY));
        debitList.add(new BaseDebit("Test3", 1000f, TypePeriod.DAY));*/
    }


    public static StubDBHelper getInstance() {
        if (instance == null) {
            synchronized (StubDBHelper.class) {
                if (instance == null) {
                    instance = new StubDBHelper();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Debit> debitList() {
        return debitList;
    }

    @Override
    public List<Credit> creditList() {
        return creditList;
    }

    @Override
    public void putDebitList(List<Debit> debits) {

    }

    @Override
    public void putCreditList(List<Credit> credits) {

    }

    @Override
    public Debit getDebitById(long id) {
        return null;
    }

    @Override
    public Credit getCreditByID(long id) {
        for (Credit c : creditList) {
            if (c.id() == id)
                return c;
        }
        return null;
    }

    @Override
    public boolean putCredit(Credit credit) {
        for (Credit c : creditList) {
            if (c.name().equals(credit.name())/* || c.id() == credit.id()*/) {
                return false;
            }
        }
        return creditList.add(credit);
    }

    @Override
    public boolean putDebit(Debit debit) {
        for (Debit d : debitList) {
            if (d.name().equals(debit.name()) /*|| d.id() == debit.id()*/) {
                return false;
            }
        }
        return debitList.add(debit);
    }

    @Override
    public String toString() {
        return debitList.toString();
    }
}
