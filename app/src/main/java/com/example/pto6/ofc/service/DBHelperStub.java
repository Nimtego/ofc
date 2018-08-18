package com.example.pto6.ofc.service;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.ToString;

@ToString
public final class DBHelperStub implements DBHelper {

    private Repository<Debit> debitRepository;
    private Repository<Credit> creditRepository;

    //todo: dagger-way
    @Getter
    private static DBHelper instance = new DBHelperStub();

    public DBHelperStub() {
        this.debitRepository = OfcApplication.getDBComponent().getDebitRepository();
        this.creditRepository = OfcApplication.getDBComponent().getCreditRepository();
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
