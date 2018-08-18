package com.example.pto6.ofc.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.dto.DebitDTO;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.service.DBHelper;
import com.example.pto6.ofc.utils.CommonUtils;
import com.example.pto6.ofc.view.DataEntryView;
import com.example.pto6.ofc.view.toast.SimpleToastAlarm;
import com.example.pto6.ofc.view.toast.ToastAlarm;

import java.util.Date;

import javax.inject.Inject;

public class DataEntryPresenter<T extends DataEntryView>
                                    extends AbstractBasePresenter<T>
                                    implements DataPresenter<T> {

    private static final String TAG = "DataEntryPresenter";

    private DBHelper mDBHelper;

    @Inject
    public DataEntryPresenter() {
        mDBHelper = OfcApplication.getDBComponent().getDBHelper();
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
/*                Debit debit = Debit.builder()
                        .name("Test")
                        .arrival(100f)
                        .changeDate(new Date())
                        .createDate(new Date())
                        .build();*/
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

    @Override
    public void takeDTO(DebitDTO debitDTO) {
        Float ammount = Float.valueOf(debitDTO.getAmount());
        String name = debitDTO.getName();
        Date create = new Date();
        Date change = new Date();
        Debit debit = Debit.builder()
                .arrival(ammount)
                .name(name)
                .createDate(create)
                .changeDate(change)
                .build();
        mDBHelper.putDebit(debit);
        CommonUtils.showLoadingDialog((Context) getView());
        getView().onBackPressed();
    }
}
