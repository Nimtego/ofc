package com.example.pto6.ofc.component;


import com.example.pto6.ofc.service.DBHelper;

import dagger.Component;

@Component(modules = DatabaseModule.class)
public interface DBComponent {
    DBHelper getDBHelper();
}
