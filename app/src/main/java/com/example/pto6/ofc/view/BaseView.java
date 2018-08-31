package com.example.pto6.ofc.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pto6.ofc.contracts.Contract;

public abstract class BaseView<P extends Contract.Presenter>
        extends AppCompatActivity
        implements Contract.View<P> {

    protected P mPresenter;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = supplyPresenter();
        mPresenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void intent() {
        Intent intent = new Intent(this, mPresenter.getNextActivity());
        (this).startActivity(intent);
    }
}
