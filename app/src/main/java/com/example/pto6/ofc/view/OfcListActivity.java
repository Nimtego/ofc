package com.example.pto6.ofc.view;


import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.contracts.OfcContract;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.utils.TabType;
import com.example.pto6.ofc.view.toast.SimpleToastAlarm;
import com.example.pto6.ofc.view.toast.ToastAlarm;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static android.widget.Toast.LENGTH_SHORT;


public class OfcListActivity extends BaseView<OfcContract.Presenter>
        implements OfcContract.View<OfcContract.Presenter>, TabLayout.OnTabSelectedListener, RecyclerView.OnItemTouchListener {


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
        // setUnBinder(ButterKnife.bind(this));
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.viewIsReady();
    }

    private void init() {
        setUpRecyclerView();
        tabs.addTab(tabs.newTab().setText("Debit"));
        tabs.addTab(tabs.newTab().setText("Credit"));
        tabs.addTab(tabs.newTab().setText("Data"));
        tabs.addOnTabSelectedListener(this);
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

        // mRecyclerView.addOnItemTouchListener(mPresenter);
    }
    @OnClick(R.id.fab)
    public void fabSinglePressing(View view) {
        mPresenter.pushFab();
    }

    @OnLongClick(R.id.fab)
    public boolean fabLongPressing() {
        Toast.makeText(this, "Fab Long Pressing ", LENGTH_SHORT).show();
        return true;
    }
/*    @OnItemClick(R2.id.list_of_things) void onItemClick(int position) {
        Toast.makeText(this, "You clicked: " + adapter.getItem(position), LENGTH_SHORT).show();
    }*/

  /*  @Override
    public Presenter setPresenter() {
        return OfcApplication.getPresenterComponent().getOfcListPresenter();
    }

    @Override
    public void setUserFinance(RecyclerView.Adapter mAdapter) {
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public TabLayout getTabLayout() {
        return tabs;
    }*/

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        OfcListActivity ofcListActivity = this;
        ClickListener clicklistener = new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(ofcListActivity, "Single Click on position        :" + position,
                        Toast.LENGTH_SHORT).show();
                mPresenter.pushInRV(position);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(ofcListActivity, "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();
                mPresenter.longPushInRV(position);
            }
        };

        GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = ofcListActivity.mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    clicklistener.onLongClick(child, ofcListActivity
                            .mRecyclerView.getChildAdapterPosition(child));
                }
            }
        });


        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && gestureDetector.onTouchEvent(e)) {
            clicklistener.onClick(child, rv.getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void setDebitListView(List<? extends Debit> listDebit) {
        RecyclerView.Adapter adapter = CardAdapter.of(listDebit, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setCredittListView(List<? extends Credit> listCredit) {
        RecyclerView.Adapter adapter = CardAdapter.of(listCredit, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public TabType getState() {
        switch (tabs.getSelectedTabPosition()) {
            case 0: return TabType.DEBIT;
            case 1: return TabType.CREDIT;
            case 2: return TabType.DATA;
            default: return null;
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
    public void onTabSelected(TabLayout.Tab tab) {
        mPresenter.tabLayoutSelect(getState());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
