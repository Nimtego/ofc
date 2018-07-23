package com.example.pto6.ofc.model;

import java.util.ArrayList;
import java.util.List;

public class IncomeExpenseImp implements IncomeExpense {

    private List<Debit> debitList;
    private List<Credit> creditList;

    public IncomeExpenseImp() {
        this.debitList = new ArrayList<>();
        this.creditList = new ArrayList<>();
    }

    public IncomeExpenseImp(List<Debit> debitList, List<Credit> creditList) {
        this.debitList = debitList;
        this.creditList = creditList;
    }

    @Override
    public List<Debit> getDebits() {
        return debitList;
    }

    @Override
    public List<Credit> getCredits() {
        return creditList;
    }

    @Override
    public Debit getDebitById(long id) {
        for (Debit d : debitList) {
            if (d.id() == id)
                return d;
        }
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
            if (c.name().equals(credit.name()) || c.id() == credit.id()) {
                return false;
            }
        }
        return creditList.add(credit);
    }

    @Override
    public boolean putDebit(Debit debit) {
        for (Debit d : debitList) {
            if (d.name().equals(debit.name()) || d.id() == debit.id()) {
                return false;
            }
        }
        return debitList.add(debit);
    }

    @Override
    public boolean removeCredit(long id) {
        return false;
    }

    @Override
    public boolean removeDebit(long id) {
        return false;
    }
}
