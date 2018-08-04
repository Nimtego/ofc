package com.example.pto6.ofc.view;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TableLayout;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.presenter.OfcListPresenter;
import com.example.pto6.ofc.presenter.Presenter;


public class OfcListActivity extends AbstractView {
    private RecyclerView mRecyclerView;
    private FloatingActionButton fab;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofc_list);
        this.tabs = findViewById(R.id.tabs);
        this.mRecyclerView = findViewById(R.id.recycler_view);
        setUpRecyclerView();
        tabs.addTab(tabs.newTab().setText("Debit"));
        tabs.addTab(tabs.newTab().setText("Credit"));
        tabs.addTab(tabs.newTab().setText("Data"));
        tabs.addOnTabSelectedListener(mPresenter);
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
        mRecyclerView.addOnItemTouchListener((RecyclerView.OnItemTouchListener) mPresenter);
    }

    @Override
    public Presenter setPresenter() {
        return new OfcListPresenter();
    }

    public void setUserFinance(RecyclerView.Adapter mAdapter) {
        mRecyclerView.setAdapter(mAdapter);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
    public TabLayout getTabLayout() {
        return tabs;
    }
}
