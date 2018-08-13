package com.example.pto6.ofc.component;

import com.example.pto6.ofc.model.DBHelper;
import com.example.pto6.ofc.model.StubDBHelper;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    DBHelper getStubDBhelper() {
        return new StubDBHelper();
    }
}
