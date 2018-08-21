package com.example.pto6.ofc.view;


import android.app.Fragment;
import android.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.dto.CreditDTO;
import com.example.pto6.ofc.dto.DebitDTO;
import com.example.pto6.ofc.dto.UserFinanceDTO;
import com.example.pto6.ofc.presenter.DataEntryPresenter;
import com.example.pto6.ofc.presenter.Presenter;
import com.example.pto6.ofc.view.fragments.AddCreditFragment;
import com.example.pto6.ofc.view.fragments.AddDebitFragment;
import com.example.pto6.ofc.view.fragments.BaseFragment;

public class DataEntryActivity extends AbstractView implements DataEntryView, AddCreditFragment.OnSomeEventListener{

    private static final String TAG = "DataEntryActivity";
    private BaseFragment mFragment;
    private FragmentTransaction mFragmentTransaction;
    TextView cap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);



        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
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
        if (view.getId() == R.id.button_add) {
            Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_form);
            TextView name = fragment.getView().findViewById(R.id.input_name_edit_text);
            TextView ammount = fragment.getView().findViewById(R.id.input_amount_edit_text);
            UserFinanceDTO dto = null;
            if (fragment instanceof AddDebitFragment) {
                dto = DebitDTO.builder()
                        .name(String.valueOf(name.getText()))
                        .amount(ammount.getText().toString())
                        .build();
            }
            if (fragment instanceof AddCreditFragment) {
                dto = CreditDTO.builder()
                        .name(String.valueOf(name.getText()))
                        .amount(ammount.getText().toString())
                        .build();
            }
            if(dto == null) {
                onError("DTO == NULL");
                mPresenter.onClick(view);
            }
            ((DataEntryPresenter) mPresenter).takeDTO(dto);
        }
        else mPresenter.onClick(view);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        mFragment = null;
        mFragmentTransaction = null;
        super.onDestroy();
    }

    @Override
    public DebitDTO getDTO() {
        UserFinanceDTO userFinanceDTO = mFragment.getDTO();
        if(mFragment instanceof AddDebitFragment)
            return (DebitDTO) userFinanceDTO;
        else
            return null; // TODO: 14.08.2018
    }
}
