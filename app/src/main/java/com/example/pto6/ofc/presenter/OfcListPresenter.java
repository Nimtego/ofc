package com.example.pto6.ofc.presenter;

import android.view.View;

import com.example.pto6.ofc.model.DBHelper;
import com.example.pto6.ofc.model.IncomeExpense;
import com.example.pto6.ofc.model.IncomeExpenseImp;
import com.example.pto6.ofc.model.StubDBHelper;

public class OfcListPresenter extends AbstractBasePresenter{

    private DBHelper dbHelper;

    public OfcListPresenter() {
        this.dbHelper = StubDBHelper.getInstance();

    }

    @Override
    Class getNextActivity() {
        return null;
    }

    @Override
    public void onClick(View view) {

    }
}
