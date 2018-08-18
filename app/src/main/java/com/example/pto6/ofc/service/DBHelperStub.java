package com.example.pto6.ofc.service;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.repository.Repository;
import com.example.repository.stub.RepositoryStub;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class DBHelperStub implements DBHelper {

    private static volatile DBHelper instance;
    private Repository<Debit> debitRepository;
    private Repository<Credit> creditRepository;

    private DBHelperStub() {
        this.debitRepository = new RepositoryStub<>();
        this.creditRepository = new RepositoryStub<>();
       /* debitList.add(new Debit("Test", 1000f, TypePeriod.DAY));
        debitList.add(new Debit("Test2", 1000f, TypePeriod.DAY));
        debitList.add(new Debit("Test3", 1000f, TypePeriod.DAY));*/
    }


    public static DBHelper getInstance() {
        if (instance == null) {
            synchronized (DBHelperStub.class) {
                if (instance == null) {
                    instance = new DBHelperStub();
                }
            }
        }
        return instance;
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
        return null;
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

    @Override
    public String toString() {
        return "DBHelperStub{" +
                "debitRepository=" + debitRepository +
                ", creditRepository=" + creditRepository +
                '}';
    }
}
