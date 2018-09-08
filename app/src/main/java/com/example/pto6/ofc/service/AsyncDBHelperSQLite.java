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
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

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
    public void debitList(Consumer<List<Debit>> onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                ArrayList<Debit> debits = new ArrayList<>(debitRepository.getAll());
                onSuccess.accept(debits);
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void creditList(Consumer<List<Credit>> onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                ArrayList<Credit> credits = new ArrayList<>(creditRepository.getAll());
                onSuccess.accept(credits);
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void putDebitList(List<Debit> debits, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                debits.forEach(debitRepository::save);
                onSuccess.run();
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void putCreditList(List<Credit> credits, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                credits.forEach(creditRepository::save);
                onSuccess.run();
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void getDebitById(long id, Consumer<? super Debit> onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                Debit one = debitRepository.getOne(id);
                onSuccess.accept(one);
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void getCreditByID(long id, Consumer<? super Credit> onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                Credit one = creditRepository.getOne(id);
                onSuccess.accept(one);
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void putCredit(Credit credit, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                creditRepository.save(credit);
                onSuccess.run();
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void putDebit(Debit debit, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                debitRepository.save(debit);
                onSuccess.run();
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void removeDebit(Debit debit, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                debitRepository.delete(debit.getId());
                onSuccess.run();
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void removeByNameDebit(String name, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                boolean present = debitRepository.getAll()
                        .stream()
                        .filter(dbt -> dbt.getName().equals(name))
                        .map(dbt -> debitRepository.delete(dbt.getId()))
                        .findAny()
                        .isPresent();
                if (present) {
                    onSuccess.run();
                } else {
                    onError.accept(new IllegalArgumentException("No entry with name [" + name + "] found"));
                }
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void removeCredit(Credit credit, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                creditRepository.delete(credit.getId());
                onSuccess.run();
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    @Override
    public void removeByNameCredit(String name, Runnable onSuccess, Consumer<? super Throwable> onError) {
        CompletableFuture.runAsync(() -> {
            try {
                sleep();
                boolean present = creditRepository.getAll()
                        .stream()
                        .filter(dbt -> dbt.getName().equals(name))
                        .map(dbt -> creditRepository.delete(dbt.getId()))
                        .findAny()
                        .isPresent();
                if (present) {
                    onSuccess.run();
                } else {
                    onError.accept(new IllegalArgumentException("No entry with name [" + name + "] found"));
                }
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
            int i = new Random().nextInt(10);
            if (i < 3)
                throw new RuntimeException("Can't connect to database");
        } catch (InterruptedException ignored) {
        }
    }
}
