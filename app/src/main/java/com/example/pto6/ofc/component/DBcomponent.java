package com.example.pto6.ofc.component;

import com.example.pto6.ofc.model.DBHelper;

import dagger.Component;

@Component(modules = DatabaseModule.class)
public interface DBcomponent {
    DBHelper getDBhelper();
}
