package com.example.pto6.ofc.view;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.presenter.OfcListPresenter;
import com.example.pto6.ofc.presenter.Presenter;



public class OfcListActivity extends AbstractView {
    private RecyclerView mRecyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofc_list);
        this.mRecyclerView = findViewById(R.id.recycler_view);
        setUpRecyclerView();
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Credit"));
        tabs.addTab(tabs.newTab().setText("Debit"));
        tabs.addTab(tabs.newTab().setText("Data"));
        this.fab = findViewById(R.id.fab);
        fab.setOnClickListener(mPresenter);
        mPresenter.viewReady();
    }

    private void setUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public Presenter setPresenter() {
        return new OfcListPresenter();
    }

    public void setUserFinance(RecyclerView.Adapter mAdapter) {
        mRecyclerView.setAdapter(mAdapter);
    }

    /*private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofc_list);
        this.mRecyclerView = findViewById(R.id.recycle_view_finance);
        this.fab = findViewById(R.id.fab);
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
    }*/
}
