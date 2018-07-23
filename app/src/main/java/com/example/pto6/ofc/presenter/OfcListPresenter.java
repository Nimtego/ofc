package com.example.pto6.ofc.presenter;

import android.view.View;
import android.widget.AdapterView;

import com.example.pto6.ofc.model.DBHelper;
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
        // TODO: 23.07.2018  
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO: 23.07.2018  
    }
}
