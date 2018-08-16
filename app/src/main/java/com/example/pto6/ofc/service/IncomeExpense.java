package com.example.pto6.ofc.service;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;

import java.util.List;

public interface IncomeExpense {
    List<Debit> getDebits();

    List<Credit> getCredits();

    Debit getDebitById(long id);

    Credit getCreditByID(long id);

    boolean putCredit(Credit credit);

    boolean putDebit(Debit debit);

    boolean removeCredit(long id);

    boolean removeDebit(long id);
}
