package com.example.pto6.ofc.presenter;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.model.DBHelper;
import com.example.pto6.ofc.view.DataEntryView;
import com.example.pto6.ofc.view.toast.SimpleToastAlarm;
import com.example.pto6.ofc.view.toast.ToastAlarm;

import javax.inject.Inject;

public class DataEntryPresenter<T extends DataEntryView>
                                    extends AbstractBasePresenter<T>
                                    implements DataPresenter<T> {

    private DBHelper mDBHelper;

    @Inject
    public DataEntryPresenter() {
        mDBHelper = OfcApplication.getDBcomponent().getDBhelper();
    }

    @Override
    Class getNextActivity() {
        return null;
    }

    @Override
    public void viewReady() {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add:
                ToastAlarm ta = new SimpleToastAlarm(getContext());
                ta.message("ADD Button in fragment");
                /*                getDataFromView();*/
                break;
            case R.id.button_cancel:
                getView().onBackPressed();
                break;
        }
    }

    private void getDataFromView() {
        TextView cap = ((Activity)getView()).findViewById(R.id.cap); // TODO: 06.08.2018 убрать колхоз
        if (cap.getText().equals("Debit")) { // TODO: 06.08.2018 убрать колхоз
            /*Debit dto = commonView.getUserData();*/
        }


    }

}
