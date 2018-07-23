package com.example.pto6.ofc.view;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.presenter.OfcListPresenter;
import com.example.pto6.ofc.presenter.Presenter;


public class OfcListActivity extends AbstractView {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofc_list);
        this.mRecyclerView = findViewById(R.id.cv);
        this.fab = findViewById(R.id.add);
        fab.setOnClickListener(mPresenter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.viewReady();
    }

    @Override
    public Presenter setPresenter() {
        return new OfcListPresenter();
    }

    public void setUserFinance(RecyclerView.Adapter mAdapter) {
        mRecyclerView.setAdapter(mAdapter);
    }
}
