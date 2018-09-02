package com.example.pto6.ofc.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.contracts.OfcContract;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.utils.RecyclerItemClickListener;
import com.example.pto6.ofc.utils.TabSelectedListener;
import com.example.pto6.ofc.utils.TabType;
import com.example.pto6.ofc.view.toast.SimpleToastAlarm;
import com.example.pto6.ofc.view.toast.ToastAlarm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static android.widget.Toast.LENGTH_SHORT;


public class OfcListActivity extends BaseView<OfcContract.Presenter>
        implements OfcContract.View<OfcContract.Presenter> {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tabs)
    TabLayout tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofc_list);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        TabLayout.Tab tab = tabs.getTabAt(0);
        if (tab != null)
            tab.select();
        mPresenter.viewIsReady();
    }

    private void init() {
        setUpRecyclerView();
        tabs.addTab(tabs.newTab().setText("Debit"));
        tabs.addTab(tabs.newTab().setText("Credit"));
        tabs.addTab(tabs.newTab().setText("Data"));
        tabs.addOnTabSelectedListener((TabSelectedListener) t -> mPresenter.tabLayoutSelect(getState()));
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
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                toast("Single Click on position:\n" + position);
                                mPresenter.pushInRV(position);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                toast("Long press on position:\n" + position);
                                mPresenter.longPushInRV(position);
                            }
                        })
        );
    }

    @OnClick(R.id.fab)
    public void fabSinglePressing(View view) {
        toast("Fab pushed");
        mPresenter.pushFab();
    }

    @OnLongClick(R.id.fab)
    public boolean fabLongPressing() {
        Toast.makeText(this, "Fab Long Pressing ", LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void setDebitListView(List<? extends Debit> listDebit) {
        RecyclerView.Adapter adapter = CardAdapter.of(listDebit, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setCreditListView(List<? extends Credit> listCredit) {
        RecyclerView.Adapter adapter = CardAdapter.of(listCredit, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void clearList() {
        // TODO: 01.09.2018 залипуха исправить
        RecyclerView.Adapter adapter = CardAdapter.of(new ArrayList<>(), this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public TabType getState() {
        switch (tabs.getSelectedTabPosition()) {
            case 0:
                return TabType.DEBIT;
            case 1:
                return TabType.CREDIT;
            case 2:
                return TabType.DATA;
            default:
                return null;
        }
    }

    @Override
    public void toast(String message) {
        ToastAlarm toastAlarm = new SimpleToastAlarm(this);
        toastAlarm.message(message);
    }

    @Override
    public OfcContract.Presenter supplyPresenter() {
        return OfcApplication.getPresenterComponent().getOfcListPresenter();
    }

    @Override
    public void intent(String tabType) {
        Intent intent = new Intent(this, mPresenter.getNextActivity());
        intent.putExtra("TYPE", tabType);
        (this).startActivity(intent);
    }
}
