package com.example.pto6.ofc.model;

import com.example.pto6.ofc.service.DBHelper;

import java.util.List;

public class TestDb implements DBHelper {

    private String str;



    public TestDb() {
        str = "DB";
    }

    @Override
    public List<Debit> debitList() {
        return null;
    }

    @Override
    public List<Credit> creditList() {
        return null;
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
        return null;
    }

    @Override
    public boolean putCredit(Credit credit) {
        return false;
    }

    @Override
    public boolean putDebit(Debit debit) {
        return false;
    }

    @Override
    public boolean removeDebit(Debit debit) {
        return false;
    }

    @Override
    public boolean removeByNameDebit(String name) {
        return false;
    }

    @Override
    public boolean removeCredit(Credit debit) {
        return false;
    }

    @Override
    public boolean removeByNameCredit(String name) {
        return false;
    }
}
