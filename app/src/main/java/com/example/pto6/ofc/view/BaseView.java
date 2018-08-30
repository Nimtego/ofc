package com.example.pto6.ofc.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pto6.ofc.contracts.Contract;
import com.example.pto6.ofc.presenter.BasePresenter;

public abstract class BaseView<P extends BasePresenter>
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

}
