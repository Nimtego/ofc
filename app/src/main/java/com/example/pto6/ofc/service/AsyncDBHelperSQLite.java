package com.example.pto6.ofc.service;

import android.content.Context;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.repository.Repository;
import com.example.repository.sqlite.GsonConverter;
import com.example.repository.sqlite.RepositoryHelper;
import com.example.repository.sqlite.RepositoryHelperSQLite;
import com.example.repository.sqlite.RepositorySQLite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class AsyncDBHelperSQLite implements AsyncDBHelper {
    private static final String FINANCE = "finance";
    private static final int VERSION = 2;
    private static final String DEBIT = "debit";
    private static final String CREDIT = "credit";

    private Repository<Debit> debitRepository;
    private Repository<Credit> creditRepository;

    private AsyncDBHelperSQLite(Repository<Debit> debitRepository, Repository<Credit> creditRepository) {
        this.debitRepository = debitRepository;
        this.creditRepository = creditRepository;
    }

    public static AsyncDBHelper get(Context context) {
        RepositoryHelper helper = new RepositoryHelperSQLite(context,
                FINANCE,
                VERSION,
                DEBIT,
                CREDIT);
        Repository<Debit> debitRepository =
                new RepositorySQLite<>(GsonConverter.forEntity(Debit.class), helper, DEBIT);
        Repository<Credit> creditRepository =
                new RepositorySQLite<>(GsonConverter.forEntity(Credit.class), helper, CREDIT);
        return new AsyncDBHelperSQLite(debitRepository, creditRepository);
    }


    void setDebitRepository(Repository<Debit> debitRepository) {
        this.debitRepository = debitRepository;
    }

    void setCreditRepository(Repository<Credit> creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public void debitList(Consumer<List<? super Debit>> onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.supplyAsync(() -> debitRepository.getAll())
                .thenApply(ArrayList::new)
                .whenComplete((list, error) -> {
                    if (list != null)
                        onSuccess.accept(list);
                    else
                        onError.accept(error);
                });
    }

    @Override
    public void creditList(Consumer<List<? super Credit>> onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.supplyAsync(() -> creditRepository.getAll())
                .thenApply(ArrayList::new)
                .whenComplete((list, error) -> {
                    if (list != null)
                        onSuccess.accept(list);
                    else
                        onError.accept(error);
                });
    }

    @Override
    public void putDebitList(List<Debit> debits, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> debits.forEach(debitRepository::save))
                .whenComplete((nothing, error) -> {
                    if (nothing != null)
                        onSuccess.run();
                    else
                        onError.accept(error);
                });
    }

    @Override
    public void putCreditList(List<Credit> credits, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> credits.forEach(creditRepository::save))
                .whenComplete((nothing, error) -> {
                    if (nothing != null)
                        onSuccess.run();
                    else
                        onError.accept(error);
                });
    }

    @Override
    public void getDebitById(long id, Consumer<? super Debit> onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.supplyAsync(() -> debitRepository.getOne(id))
                .whenComplete((unit, error) -> {
                    if (unit != null)
                        onSuccess.accept(unit);
                    else
                        onError.accept(error);
                });
    }

    @Override
    public void getCreditByID(long id, Consumer<? super Credit> onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.supplyAsync(() -> creditRepository.getOne(id))
                .whenComplete((unit, error) -> {
                    if (unit != null)
                        onSuccess.accept(unit);
                    else
                        onError.accept(error);
                });
    }

    @Override
    public void putCredit(Credit credit, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> creditRepository.save(credit))
                .whenComplete((unit, error) -> {
                    if (unit != null)
                        onSuccess.run();
                    else
                        onError.accept(error);
                });
    }

    @Override
    public void putDebit(Debit debit, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> debitRepository.save(debit))
                .whenComplete((unit, error) -> {
                    if (unit != null)
                        onSuccess.run();
                    else
                        onError.accept(error);
                });
    }

    @Override
    public void removeDebit(Debit debit, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> debitRepository.delete(debit.getId()))
                .whenComplete((unit, error) -> {
                    if (unit != null)
                        onSuccess.run();
                    else
                        onError.accept(error);
                });
    }

    @Override
    public void removeByNameDebit(String name, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.supplyAsync(() -> debitRepository.getAll())
                .thenApply(Collection::stream)
                .thenApply(s -> s.filter(u -> u.getName().equals(name)))
                .thenApply(Stream::findAny)
                .whenComplete((opt, error) -> {
                    if (opt != null) {
                        if (opt.isPresent()) {
                            debitRepository.delete(opt.get().getId());
                            onSuccess.run();
                        } else {
                            onError.accept(new IllegalArgumentException("User with name [" + name + "] not found"));
                        }
                    } else {
                        onError.accept(error);
                    }
                });
    }

    @Override
    public void removeCredit(Credit credit, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> creditRepository.delete(credit.getId()))
                .whenComplete((unit, error) -> {
                    if (unit != null)
                        onSuccess.run();
                    else
                        onError.accept(error);
                });
    }

    @Override
    public void removeByNameCredit(String name, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.supplyAsync(() -> creditRepository.getAll())
                .thenApply(Collection::stream)
                .thenApply(s -> s.filter(u -> u.getName().equals(name)))
                .thenApply(Stream::findAny)
                .whenComplete((opt, error) -> {
                    if (opt != null) {
                        if (opt.isPresent()) {
                            creditRepository.delete(opt.get().getId());
                            onSuccess.run();
                        } else {
                            onError.accept(new IllegalArgumentException("User with name [" + name + "] not found"));
                        }
                    } else {
                        onError.accept(error);
                    }
                });
    }
}
