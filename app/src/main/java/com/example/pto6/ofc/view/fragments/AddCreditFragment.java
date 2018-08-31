package com.example.pto6.ofc.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.contracts.Contract;
import com.example.pto6.ofc.dto.CreditDTO;
import com.example.pto6.ofc.dto.UserFinanceDTO;


import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCreditFragment extends BaseFragment {

    OnSomeEventListener someEventListener;
    @BindView(R.id.input_name_edit_text)
    EditText name;
    @BindView(R.id.input_amount_edit_text)
    EditText amount;

    @Override
    protected void setUp(View view) {

    }

    @Override
    public UserFinanceDTO getDTO() {
        return CreditDTO.builder().name(String.valueOf(name.getText())).amount(amount.getText().toString()).build();
    }



    @Override
    public Contract.Presenter supplyPresenter() {
        return null;
    }


    public interface OnSomeEventListener {
        void someEvent(View view);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            someEventListener = (OnSomeEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_credit, null);
        setUnBinder(ButterKnife.bind(rootView));
        name = rootView.findViewById(R.id.input_name_edit_text);
        amount = rootView.findViewById(R.id.input_amount_edit_text);
        rootView.findViewById(R.id.button_add).setOnClickListener(view -> someEventListener.someEvent(view));
        rootView.findViewById(R.id.button_cancel).setOnClickListener(view -> someEventListener.someEvent(view));
        return rootView;
    }
}
