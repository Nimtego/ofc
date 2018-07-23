package com.example.pto6.ofc.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.model.BaseDebit;
import com.example.pto6.ofc.model.DBHelper;
import com.example.pto6.ofc.model.StubDBHelper;
import com.example.pto6.ofc.model.TypePeriod;
import com.example.pto6.ofc.view.AddCreditActivity;
import com.example.pto6.ofc.view.AddDebitActivity;
import com.example.pto6.ofc.view.OfcListActivity;
import com.example.pto6.ofc.view.RecyclerAdapter;

public class OfcListPresenter extends AbstractBasePresenter{

    private DBHelper dbHelper;
    private boolean isCredit;
    private RecyclerView.Adapter adapter;

    public OfcListPresenter() {
        this.dbHelper = StubDBHelper.getInstance();
    }

    @Override
    Class getNextActivity() {
        if (isCredit)
            return AddCreditActivity.class;
        return AddDebitActivity.class;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.fab:
                /*intent();*/
                testAdd();
                viewReady();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO: 23.07.2018  
    }

    @Override
    public void viewReady() {
        adapter = new RecyclerAdapter(dbHelper.debitList(), commonView);
        ((OfcListActivity)commonView).setUserFinance(adapter);
    }
    private void testAdd() {
        dbHelper.putDebit(new BaseDebit("Test", 1000F, TypePeriod.DAY));
    }
}
