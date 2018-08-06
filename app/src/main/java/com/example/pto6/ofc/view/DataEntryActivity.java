package com.example.pto6.ofc.view;


import android.app.Fragment;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.presenter.DataEntryPresenter;
import com.example.pto6.ofc.presenter.Presenter;
import com.example.pto6.ofc.view.fragments.AddCreditFragment;
import com.example.pto6.ofc.view.fragments.AddDebitFragment;

public class DataEntryActivity extends AbstractView implements AddCreditFragment.OnSomeEventListener{

    private Fragment mFragment;
    private FragmentTransaction mFragmentTransaction;
/*
    private Button addButton;
    private Button cancelButton;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);



        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        TextView cap = findViewById(R.id.cap);
        if (type.equals("DEBIT")) {
            mFragment = new AddDebitFragment();
            cap.setText(getResources().getString(R.string.tab_layout_tab_debit));
        }
        if (type.equals("CREDIT")) {
            mFragment = new AddCreditFragment();
            cap.setText(getResources().getString(R.string.tab_layout_tab_credit));
        }
        mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.fragment_form, mFragment);
        mFragmentTransaction.commit();
/*        addButton = findViewById(R.id.button_add);
        cancelButton = findViewById(R.id.button_cancel);
        addButton.setOnClickListener(mPresenter);
        cancelButton.setOnClickListener(mPresenter);*/
    }

    @Override
    public Presenter setPresenter() {
        return OfcApplication.getPresenterComponent().getDataEntryPresenter();
    }

    @Override
    public void someEvent(View view) {
        mPresenter.onClick(view);
    }
}
