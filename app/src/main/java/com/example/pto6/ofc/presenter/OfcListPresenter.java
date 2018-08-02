package com.example.pto6.ofc.presenter;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.model.BaseCredit;
import com.example.pto6.ofc.model.BaseDebit;
import com.example.pto6.ofc.model.BaseUserFinance;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.DBHelper;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.model.StubDBHelper;
import com.example.pto6.ofc.model.TypePeriod;
import com.example.pto6.ofc.view.CardAdapter;
import com.example.pto6.ofc.view.ClickListener;
import com.example.pto6.ofc.view.OfcListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OfcListPresenter extends AbstractBasePresenter {

    private DBHelper dbHelper;
    private RecyclerView.Adapter adapter;
    private ClickListener clicklistener;
    private GestureDetector gestureDetector;
    private List<Debit> mDebitList;
    private List<Credit> mCredits;


    public OfcListPresenter() {
        this.dbHelper = StubDBHelper.getInstance();
        this.clicklistener = new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(commonView, "Single Click on position        :" + position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(commonView, "Long press on position :" + position,
                        Toast.LENGTH_LONG).show();
                if (((OfcListActivity) commonView).getTabLayout().getSelectedTabPosition() == 0) {
                    dbHelper.removeByNameDebit(((BaseUserFinance) mDebitList.get(position)).name());
                    viewReady();
                }
                if (((OfcListActivity) commonView).getTabLayout().getSelectedTabPosition() == 1) {
                    dbHelper.removeByNameCredit(((BaseUserFinance) mCredits.get(position)).name());
                    viewReady();
                }

            }
        };

        this.gestureDetector = new GestureDetector(commonView, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = ((OfcListActivity) commonView)
                        .getRecyclerView().findChildViewUnder(e.getX(), e.getY());
                if (child != null && clicklistener != null) {
                    clicklistener.onLongClick(child, ((OfcListActivity) commonView)
                            .getRecyclerView().getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    Class getNextActivity() {
        return null;
/*        if (isCredit)
            return AddCreditActivity.class;
        return AddDebitActivity.class;*/
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            testAdd();
            viewReady();
        }
    }


    @Override
    public void viewReady() {
        if (((OfcListActivity) commonView).getTabLayout().getSelectedTabPosition() == 0) {
            List<Debit> list = dbHelper.debitList();
            this.mDebitList = list;
            adapter = new CardAdapter(list, commonView);
            ((OfcListActivity) commonView).setUserFinance(adapter);
        }
        if (((OfcListActivity) commonView).getTabLayout().getSelectedTabPosition() == 1) {
            List<Credit> list = dbHelper.creditList();
            this.mCredits = list;
            adapter = new CardAdapter(list, commonView);
            ((OfcListActivity) commonView).setUserFinance(adapter);
        }
        if (((OfcListActivity) commonView).getTabLayout().getSelectedTabPosition() == 2) {
            adapter = new CardAdapter(new ArrayList(), commonView);
            ((OfcListActivity) commonView).setUserFinance(adapter);
            Toast.makeText(commonView, "IN PROGRESS",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void testAdd() {
        if (((OfcListActivity) commonView).getTabLayout().getSelectedTabPosition() == 0) {
            dbHelper.putDebit(new BaseDebit("Test Debit "
                    + new Random().nextInt(100),
                    new Random().nextFloat()
                            + new Random().nextInt(4000), TypePeriod.DAY));
        }
        if (((OfcListActivity) commonView).getTabLayout().getSelectedTabPosition() == 1) {
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
        Toast.makeText(commonView, tab.getText(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
