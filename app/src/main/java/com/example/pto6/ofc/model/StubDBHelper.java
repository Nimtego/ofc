package com.example.pto6.ofc.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class StubDBHelper implements DBHelper {

    private List<Debit> debitList;
    private List<Credit> creditList;

    @Inject
    public StubDBHelper() {
        this.debitList = new ArrayList<>();
        this.creditList = new ArrayList<>();
       /* debitList.add(new BaseDebit("Test", 1000f, TypePeriod.DAY));
        debitList.add(new BaseDebit("Test2", 1000f, TypePeriod.DAY));
        debitList.add(new BaseDebit("Test3", 1000f, TypePeriod.DAY));*/
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
    public boolean removeDebit(Debit debit) {
        return debitList.remove(debit);
    }

    @Override
    public boolean removeByNameDebit(String name) {
        for (Debit d : debitList) {
            if (((BaseUserFinance)d).name().equals(name)) {
                return removeDebit(d);
            }
        }
        return false;
    }

    @Override
    public boolean removeCredit(Credit credit) {
        return creditList.remove(credit);
    }

    @Override
    public boolean removeByNameCredit(String name) {
        for (Credit c : creditList) {
            if (((BaseUserFinance)c).name().equals(name)) {
                return removeCredit(c);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return debitList.toString();
    }
}
