package com.example.pto6.ofc.service;

import android.content.Context;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.repository.Repository;
import com.example.repository.sqlite.GsonConverter;
import com.example.repository.sqlite.RepositorySQLite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class DBHelperSQLite implements DBHelper {

    public static final String CREDIT = "credit";
    private static final String FINANCE = "finance";
    private static final String DEBIT = "debit";
    private Repository<Debit> debitRepository;
    private Repository<Credit> creditRepository;

    private DBHelperSQLite(Repository<Debit> debitRepository, Repository<Credit> creditRepository) {
        this.debitRepository = debitRepository;
        this.creditRepository = creditRepository;
    }

    public static DBHelper get(Context context) {
        Repository<Debit> debitRepository = new RepositorySQLite<>(context,
                FINANCE,
                DEBIT,
                GsonConverter.forEntity(Debit.class));
        Repository<Credit> creditRepository = new RepositorySQLite<>(context,
                FINANCE,
                CREDIT,
                GsonConverter.forEntity(Credit.class));
        return new DBHelperSQLite(debitRepository, creditRepository);
    }

    void setDebitRepository(Repository<Debit> debitRepository) {
        this.debitRepository = debitRepository;
    }

    void setCreditRepository(Repository<Credit> creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public List<Debit> debitList() {
        return (List<Debit>) debitRepository.getAll();
    }

    @Override
    public List<Credit> creditList() {
        return new ArrayList<>(creditRepository.getAll());
    }

    @Override
    public void putDebitList(List<Debit> debits) {

    }

    @Override
    public void putCreditList(List<Credit> credits) {

    }

    @Override
    public Debit getDebitById(long id) {
        return debitRepository.getOne(id);
    }

    @Override
    public Credit getCreditByID(long id) {
        return creditRepository.getOne(id);
    }

    @Override
    public boolean putCredit(Credit credit) {
        try {
            creditRepository.save(credit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean putDebit(Debit debit) {
        try {
            debitRepository.save(debit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeDebit(Debit debit) {
        return Optional
                .ofNullable(debitRepository.delete(debit.getId()))
                .isPresent();
    }

    @Override
    public boolean removeByNameDebit(String name) {
        return debitRepository.getAll()
                .stream()
                .filter(dbt -> dbt.getName().equals(name))
                .map(dbt -> debitRepository.delete(dbt.getId()))
                .findAny()
                .isPresent();
    }

    @Override
    public boolean removeCredit(Credit credit) {
        return Optional
                .ofNullable(creditRepository.delete(credit.getId()))
                .isPresent();
    }

    @Override
    public boolean removeByNameCredit(String name) {
        return creditRepository.getAll()
                .stream()
                .filter(cdt -> cdt.getName().equals(name))
                .map(cdt -> creditRepository.delete(cdt.getId()))
                .findAny()
                .isPresent();
    }
}
