package com.example.pto6.ofc.presenter;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.service.DBHelper;
import com.example.pto6.ofc.service.DBHelperSQLite;
import com.example.pto6.ofc.view.AbstractView;
import com.example.pto6.ofc.view.CardAdapter;
import com.example.pto6.ofc.view.ClickListener;
import com.example.pto6.ofc.view.DataEntryActivity;
import com.example.pto6.ofc.view.OfcView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OfcListPresenter<T extends OfcView> extends AbstractBasePresenter<T> implements OfcPresenter<T> {

    private static final String TAG = "OfcListPresenter";

    private ClickListener clicklistener;
    private GestureDetector gestureDetector;
    private List<Debit> mDebitList;
    private List<Credit> mCredits;

    @Inject
    public OfcListPresenter() {
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
                if (getView().getTabLayout().getSelectedTabPosition() == 0) {
                    dbHelper().removeByNameDebit((mDebitList.get(position)).getName());
                    viewReady();
                }
                if (getView().getTabLayout().getSelectedTabPosition() == 1) {
                    dbHelper().removeByNameCredit((mCredits.get(position)).getName());
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
                    clicklistener.onLongClick(child, getView()
                            .getRecyclerView().getChildAdapterPosition(child));
                }
            }
        });
    }

    private DBHelper dbHelper() {
        return DBHelperSQLite.get(getContext());
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
            if (type != null) {
                intent("type", type);
            } else
                viewReady();
        }
    }


    @Override
    public void viewReady() {

        OfcView ofcView = getView();
        RecyclerView.Adapter adapter;

        if (Optional.ofNullable(ofcView.getTabLayout())
                .map(TabLayout::getSelectedTabPosition)
                .map(pos -> pos == 0)
                .orElse(false)) {
            List<Debit> list = dbHelper().debitList();
            this.mDebitList = list;
            adapter = CardAdapter.of(list, (AbstractView) ofcView);
            ofcView.setUserFinance(adapter);
        }
        if (ofcView.getTabLayout().getSelectedTabPosition() == 1) {
            List<Credit> list = dbHelper().creditList();
            this.mCredits = list;
            adapter = CardAdapter.of(list, (AbstractView) ofcView);
            ofcView.setUserFinance(adapter);
        }
        if (ofcView.getTabLayout().getSelectedTabPosition() == 2) {
            adapter = CardAdapter.of(new ArrayList<>(), (AbstractView) ofcView);
            ofcView.setUserFinance(adapter);
            Toast.makeText(getContext(), "IN PROGRESS",
                    Toast.LENGTH_SHORT).show();
        }
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
