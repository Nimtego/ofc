package com.example.pto6.ofc.view.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.dto.UserFinanceDTO;

public class AddCreditFragment extends BaseFragment {

    OnSomeEventListener someEventListener;

    @Override
    protected void setUp(View view) {

    }

    @Override
    public UserFinanceDTO getDTO() {
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
        rootView.findViewById(R.id.button_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    someEventListener.someEvent(view);
                }
            });
        rootView.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        someEventListener.someEvent(view);
                    }
                });
        return rootView;
    }
}
