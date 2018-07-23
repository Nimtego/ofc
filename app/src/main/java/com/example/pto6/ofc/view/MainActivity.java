package com.example.pto6.ofc.view;

import android.os.Bundle;
import android.util.Log;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.presenter.MainPresenter;
import com.example.pto6.ofc.presenter.Presenter;


public class MainActivity extends AbstractView {

    private final String LOG_TAG = "Main activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public Presenter setPresenter() {
        return new MainPresenter();
    }

    private void init() {
       // FloatingActionButton fab = findViewById(R.id.fab);
      //  fab.setOnClickListener(mPresenter);
        Log.v(LOG_TAG, "Init");

    }
}
