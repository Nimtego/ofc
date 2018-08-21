package com.example.pto6.ofc.component;


import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.repository.Repository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    Repository<Debit> mDebitRepository;
    Repository<Credit> mCreditRepository;

    public RepositoryModule(Repository<Debit> debitRepository, Repository<Credit> creditRepository) {
        mDebitRepository = debitRepository;
        mCreditRepository = creditRepository;
    }

    @Provides
    Repository<Debit> provideDebitRepo() {
        return mDebitRepository;
    }
    @Provides
    Repository<Credit> provideCreditRepo() {
        return mCreditRepository;
    }
}
