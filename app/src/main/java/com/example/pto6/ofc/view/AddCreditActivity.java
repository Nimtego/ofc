package com.example.pto6.ofc.view;

import android.os.Bundle;


import com.example.pto6.ofc.presenter.Presenter;


public class AddCreditActivity extends AbstractView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_add_credit);
    }

    @Override
    public Presenter setPresenter() {
      //  return new AddCreditPresenter();
        return null;
    }
}
