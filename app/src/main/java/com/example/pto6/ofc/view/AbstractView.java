package com.example.pto6.ofc.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pto6.ofc.presenter.Presenter;


public abstract class AbstractView extends AppCompatActivity implements CommonView {

    protected Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = setPresenter();
        mPresenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }
}
