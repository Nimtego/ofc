package com.example.pto6.ofc.view;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.presenter.OfcListPresenter;
import com.example.pto6.ofc.presenter.Presenter;


public class OfcListActivity extends AbstractView {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofc_list);
    }

    @Override
    public Presenter setPresenter() {
        return new OfcListPresenter();
    }
}
