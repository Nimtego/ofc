package com.example.pto6.ofc.view;

import android.os.Bundle;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.presenter.AddDebitPresenter;
import com.example.pto6.ofc.presenter.Presenter;

public class AddDebitActivity extends AbstractView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debit);
    }

    @Override
    public Presenter setPresenter() {
        return new AddDebitPresenter();
    }
}
