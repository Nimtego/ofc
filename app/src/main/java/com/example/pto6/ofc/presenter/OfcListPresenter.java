package com.example.pto6.ofc.presenter;

import android.view.View;
import android.widget.AdapterView;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.model.DBHelper;
import com.example.pto6.ofc.model.StubDBHelper;
import com.example.pto6.ofc.view.AddCreditActivity;
import com.example.pto6.ofc.view.AddDebitActivity;

public class OfcListPresenter extends AbstractBasePresenter{

    private DBHelper dbHelper;
    boolean isCredit;


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
            case R.id.add:
                intent();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO: 23.07.2018  
    }
}
