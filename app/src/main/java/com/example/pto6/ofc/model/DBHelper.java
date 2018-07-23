package com.example.pto6.ofc.model;

import java.util.List;

public interface DBHelper {
    List<Debit> debitList();
    List<Credit> creditList();
    void putDebitList(List<Debit> debits);
    void putCreditList(List<Credit> credits);
    Debit getDebitById(long id);
    Credit getCreditByID(long id);
    boolean putCredit(Credit credit);
    boolean putDebit(Debit debit);
}
