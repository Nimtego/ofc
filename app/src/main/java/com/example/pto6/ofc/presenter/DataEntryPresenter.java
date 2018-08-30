package com.example.pto6.ofc.presenter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.contracts.DataEntryContract;
import com.example.pto6.ofc.dto.CreditDTO;
import com.example.pto6.ofc.dto.DebitDTO;
import com.example.pto6.ofc.dto.UserFinanceDTO;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.service.DBHelperSQLite;
import com.example.pto6.ofc.utils.CommonUtils;
import com.example.pto6.ofc.view.DataEntryView;
import com.example.pto6.ofc.view.toast.SimpleToastAlarm;
import com.example.pto6.ofc.view.toast.ToastAlarm;

import java.util.Date;

import javax.inject.Inject;

public class DataEntryPresenter<T extends DataEntryContract.DataEntryView>
                                    extends AbstractBasePresenter<T>
                                    implements DataEntryContract.DataPresenter<T> {

    private static final String TAG = "DataEntryPresenter";

//    private DBHelper mDBHelper;

    @Inject
    public DataEntryPresenter() {
//        mDBHelper = OfcApplication.getDBComponent().getDBHelper();
    }

    @Override
    Class getNextActivity() {
        return null;
    }

/*    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add:
                ToastAlarm ta = new SimpleToastAlarm(getContext());
                ta.message("ADD Button in fragment");
                break;
            case R.id.button_cancel:
                getView().onBackPressed();
                break;
        }
    }*/

    @Override
    public void takeDTO(UserFinanceDTO dto) {
        Date date = new Date();
        if (dto instanceof DebitDTO) {
            DebitDTO debitDTO = (DebitDTO) dto;
            Float ammount = Float.valueOf(debitDTO.getAmount());
            String name = debitDTO.getName();
            Debit debit = Debit.builder()
                    .arrival(ammount)
                    .name(name)
                    .createDate(date)
                    .changeDate(date)
                    .build();
            DBHelperSQLite.get(getContext()).putDebit(debit);
            CommonUtils.showLoadingDialog((Context) getView());
        }
        if (dto instanceof CreditDTO) {
            CreditDTO creditDTO = (CreditDTO) dto;
            Float ammount = Float.valueOf(creditDTO.getAmount());
            String name = creditDTO.getName();
            Credit credit = Credit.builder()
                    .arrivalSize(ammount)
                    .name(name)
                    .createDate(date)
                    .changeDate(date)
                    .build();
            DBHelperSQLite.get(getContext()).putCredit(credit);
            CommonUtils.showLoadingDialog((Context) getView());
        }
        getView().onBackPressed();
    }
}
