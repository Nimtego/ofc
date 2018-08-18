package com.example.pto6.ofc.view.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.dto.DebitDTO;
import com.example.pto6.ofc.dto.UserFinanceDTO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDebitFragment extends BaseFragment {
    private static final String TAG = "<< AddDebitFragment >> - ";
    AddCreditFragment.OnSomeEventListener someEventListener;

    @BindView(R.id.input_name_edit_text)
    EditText name;
    @BindView(R.id.input_amount_edit_text)
    EditText amount;
    @BindView(R.id.button_add)
    Button add;
    @BindView(R.id.button_cancel)
    Button cancel;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            someEventListener = (AddCreditFragment.OnSomeEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View rootView = inflater.inflate(R.layout.fragment_add_debit, null);
       // ButterKnife.bind(this, rootView);
        Log.v(TAG, String.valueOf(rootView == null));
        assert rootView != null;
        setUnBinder(ButterKnife.bind(rootView));
        name = rootView.findViewById(R.id.input_name_edit_text);
        add = rootView.findViewById(R.id.button_add);
        cancel = rootView.findViewById(R.id.button_cancel);
        add.setOnClickListener(view -> someEventListener.someEvent(view));
        cancel.setOnClickListener(view -> someEventListener.someEvent(view));
        return rootView;
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public UserFinanceDTO getDTO() {
        return DebitDTO.builder().name(String.valueOf(name.getText())).amount(amount.getText().toString()).build();
    }


}
