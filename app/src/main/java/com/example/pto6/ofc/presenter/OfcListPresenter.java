package com.example.pto6.ofc.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.model.BaseDebit;
import com.example.pto6.ofc.model.DBHelper;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.model.StubDBHelper;
import com.example.pto6.ofc.model.TypePeriod;
import com.example.pto6.ofc.view.CardAdapter;
import com.example.pto6.ofc.view.ClickListener;
import com.example.pto6.ofc.view.OfcListActivity;

import java.util.List;
import java.util.Random;

public class OfcListPresenter extends AbstractBasePresenter{

    private DBHelper dbHelper;
    private RecyclerView.Adapter adapter;
    private ClickListener clicklistener;
    private GestureDetector gestureDetector;

    public OfcListPresenter() {
        this.dbHelper = StubDBHelper.getInstance();
        this.clicklistener = new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Toast.makeText(commonView, "Single Click on position        :"+position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(commonView, "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        };

        this.gestureDetector = new GestureDetector(commonView, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = ((OfcListActivity)commonView)
                        .getRecyclerView().findChildViewUnder(e.getX(),e.getY());
                if(child!=null && clicklistener!=null){
                    clicklistener.onLongClick(child,((OfcListActivity)commonView)
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
        switch(view.getId())
        {
            case R.id.fab:
                /*intent();*/
                testAdd();
                viewReady();
                break;
        }
    }


    @Override
    public void viewReady() {
        List<Debit> list = dbHelper.debitList();
        adapter = new CardAdapter(list, commonView);
        ((OfcListActivity)commonView).setUserFinance(adapter);
    }
    private void testAdd() {
        dbHelper.putDebit(new BaseDebit("Test"+ new Random().nextInt(100),
                new Random().nextFloat(), TypePeriod.DAY));
        System.out.println(dbHelper);
    }
    public Adapter getAdapter() {
        return (Adapter) adapter;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(),e.getY());
        if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
            clicklistener.onClick(child,rv.getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
