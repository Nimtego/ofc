package com.example.pto6.ofc.component;


import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.service.DBHelper;
import com.example.pto6.ofc.service.DBHelperStub;
import com.example.repository.Repository;
import com.example.repository.stub.RepositoryStub;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {


    @Provides
    DBHelper getDBHelper() {
        return DBHelperStub.getInstance();
    }

    @Provides
    Repository<Debit> getDebitRepository() {
        return new RepositoryStub<>();
    }

    @Provides
    Repository<Credit> getCreditRepository() {
        return new RepositoryStub<>();
    }
}
