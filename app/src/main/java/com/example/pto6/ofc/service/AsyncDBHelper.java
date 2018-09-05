package com.example.pto6.ofc.service;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;

import java.util.List;
import java.util.function.Consumer;

public interface AsyncDBHelper {

    void debitList(Consumer<List<? super Debit>> onSuccess, Consumer<? super Throwable> onError);

    void creditList(Consumer<List<? super Credit>> onSuccess, Consumer<? super Throwable> onError);

    void putDebitList(List<Debit> debits, Runnable onSuccess, Consumer<? super Throwable> onError);

    void putCreditList(List<Credit> credits, Runnable onSuccess, Consumer<? super Throwable> onError);

    void getDebitById(long id, Consumer<? super Debit> onSuccess, Consumer<? super Throwable> onError);

    void getCreditByID(long id, Consumer<? super Credit> onSuccess, Consumer<? super Throwable> onError);

    void putCredit(Credit credit, Runnable onSuccess, Consumer<? super Throwable> onError);

    void putDebit(Debit debit, Runnable onSuccess, Consumer<? super Throwable> onError);

    void removeDebit(Debit debit, Runnable onSuccess, Consumer<? super Throwable> onError);

    void removeByNameDebit(String name, Runnable onSuccess, Consumer<? super Throwable> onError);

    void removeCredit(Credit debit, Runnable onSuccess, Consumer<? super Throwable> onError);

    void removeByNameCredit(String name, Runnable onSuccess, Consumer<? super Throwable> onError);
}
