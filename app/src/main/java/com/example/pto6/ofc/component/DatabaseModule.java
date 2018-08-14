package com.example.pto6.ofc.component;


import com.example.pto6.ofc.service.DBHelper;
import com.example.pto6.ofc.service.DBHelperStub;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    DBHelper getStubDBhelper() {
        return DBHelperStub.getInstance();
    }
}
