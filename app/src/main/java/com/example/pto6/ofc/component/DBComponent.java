package com.example.pto6.ofc.component;


import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.service.DBHelper;
import com.example.repository.Repository;

import dagger.Component;


@Component(modules = DatabaseModule.class)
public interface DBComponent {
    DBHelper getDBHelper();

    Repository<Debit> getDebitRepository();

    Repository<Credit> getCreditRepository();
}
