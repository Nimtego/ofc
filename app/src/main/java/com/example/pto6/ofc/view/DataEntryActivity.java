package com.example.pto6.ofc.view;


import android.app.Fragment;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.contracts.DataEntryContract;
import com.example.pto6.ofc.dto.UserFinanceDTO;
import com.example.pto6.ofc.view.fragments.AddCreditFragment;
import com.example.pto6.ofc.view.fragments.AddDebitFragment;
import com.example.pto6.ofc.view.fragments.BaseFragment;

public class DataEntryActivity extends BaseView<DataEntryContract.Presenter>
                        implements DataEntryContract.View<DataEntryContract.Presenter>,
                                    AddCreditFragment.OnSomeEventListener{

    private static final String TAG = "DataEntryActivity";
    private BaseFragment mFragment;
    private FragmentTransaction mFragmentTransaction;
    TextView cap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);



        Intent intent = getIntent();
        String type = intent.getStringExtra("TYPE");
        cap = findViewById(R.id.cap);
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
    }
    @Override
    public DataEntryContract.Presenter supplyPresenter() {
        return  OfcApplication
                .getPresenterComponent()
                .getDataEntryPresenter();
    }

    @Override
    public void someEvent(View view) {
        if (view.getId() == R.id.button_add) {
            Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_form);
            mPresenter.takeDTO(mFragment.getDTO());
        }
        else onBackPressed();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        mFragment = null;
        mFragmentTransaction = null;
        super.onDestroy();
    }

    @Override
    public UserFinanceDTO getDTO() {
        return mFragment.getDTO();
    }

}
