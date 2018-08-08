package com.example.pto6.ofc.presenter;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.model.BaseCredit;
import com.example.pto6.ofc.model.BaseDebit;
import com.example.pto6.ofc.model.BaseUserFinance;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.DBHelper;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.model.TypePeriod;
import com.example.pto6.ofc.view.AbstractView;
import com.example.pto6.ofc.view.CardAdapter;
import com.example.pto6.ofc.view.ClickListener;
import com.example.pto6.ofc.view.DataEntryActivity;
import com.example.pto6.ofc.view.OfcView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

public class OfcListPresenter<T extends OfcView> extends AbstractBasePresenter<T> implements OfcPresenter<T>{

    private DBHelper dbHelper;
    private RecyclerView.Adapter adapter;
    private ClickListener clicklistener;
    private GestureDetector gestureDetector;
    private List<Debit> mDebitList;
    private List<Credit> mCredits;

    @Inject
    public OfcListPresenter() {
        this.dbHelper = OfcApplication.getDBcomponent().getDBhelper();
        this.clicklistener = new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(getContext(), "Single Click on position        :" + position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();
                if ( getView().getTabLayout().getSelectedTabPosition() == 0) {
                    dbHelper.removeByNameDebit(((BaseUserFinance) mDebitList.get(position)).name());
                    viewReady();
                }
                if (getView().getTabLayout().getSelectedTabPosition() == 1) {
                    dbHelper.removeByNameCredit(((BaseUserFinance) mCredits.get(position)).name());
                    viewReady();
                }

            }
        };

        this.gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = getView()
                        .getRecyclerView().findChildViewUnder(e.getX(), e.getY());
                if (child != null && clicklistener != null) {
                    clicklistener.onLongClick(child,  getView()
                            .getRecyclerView().getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    Class getNextActivity() {
        return DataEntryActivity.class;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            int tabsNumber = getView().getTabLayout().getSelectedTabPosition();
            String type = null;
            switch (tabsNumber) {
                case 0:
                    type = "DEBIT";
                    break;
                case 1:
                    type = "CREDIT";
                    break;
            }
            System.out.println(type);
            if (type != null) {
                intent("type", type);
            }
            else
                viewReady();
        }
    }


    @Override
    public void viewReady() {
        OfcView ofcView = getView();
        if (ofcView.getTabLayout().getSelectedTabPosition() == 0) {
            List<Debit> list = dbHelper.debitList();
            this.mDebitList = list;
            adapter = new CardAdapter(list, (AbstractView) ofcView);
            ofcView.setUserFinance(adapter);
        }
        if (ofcView.getTabLayout().getSelectedTabPosition() == 1) {
            List<Credit> list = dbHelper.creditList();
            this.mCredits = list;
            adapter = new CardAdapter(list, (AbstractView) ofcView);
            ofcView.setUserFinance(adapter);
        }
        if (ofcView.getTabLayout().getSelectedTabPosition() == 2) {
            adapter = new CardAdapter(new ArrayList(), (AbstractView) ofcView);
            ofcView.setUserFinance(adapter);
            Toast.makeText(getContext(), "IN PROGRESS",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void testAdd() {
        OfcView ofcView = getView();
        if (ofcView.getTabLayout().getSelectedTabPosition() == 0) {
            dbHelper.putDebit(new BaseDebit("Test Debit "
                    + new Random().nextInt(100),
                    new Random().nextFloat()
                            + new Random().nextInt(4000), TypePeriod.DAY));
        }
        if (ofcView.getTabLayout().getSelectedTabPosition() == 1) {
            Credit credit = BaseCredit.newBuilder("Test Credit "
                    + new Random().nextInt(100))
                    .arrival(new Random().nextFloat() + new Random().nextInt(4000)).build();
            dbHelper.putCredit(credit);
        }

        System.out.println(dbHelper);
    }

    public Adapter getAdapter() {
        return (Adapter) adapter;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
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
    public void onTabSelected(TabLayout.Tab tab) {
        viewReady();
        Toast.makeText(getContext(), tab.getText(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
