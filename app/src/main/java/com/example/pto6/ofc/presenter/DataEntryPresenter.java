package com.example.pto6.ofc.presenter;

import android.content.Context;

import com.example.pto6.ofc.contracts.DataEntryContract;
import com.example.pto6.ofc.dto.CreditDTO;
import com.example.pto6.ofc.dto.DebitDTO;
import com.example.pto6.ofc.dto.UserFinanceDTO;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.service.DBHelperSQLite;
import com.example.pto6.ofc.utils.CommonUtils;

import java.util.Date;

import javax.inject.Inject;

public class DataEntryPresenter extends BasePresenter<DataEntryContract.View>
        implements DataEntryContract.Presenter<DataEntryContract.View> {

    private static final String TAG = "DataEntryPresenter";

//    private DBHelper mDBHelper;

    @Inject
    public DataEntryPresenter() {
//        mDBHelper = OfcApplication.getDBComponent().getDBHelper();
    }

    @Override
    public Class getNextActivity() {
        return null;
    }

    @Override
    public void addButtonPressed() {
        UserFinanceDTO dto = getView().getFormData();
        Date date = new Date();
        if (dto instanceof DebitDTO) {
            DebitDTO debitDTO = (DebitDTO) dto;
            Float amount = Float.valueOf(debitDTO.getAmount());
            String name = debitDTO.getName();
            Debit debit = Debit.builder()
                    .arrival(amount)
                    .name(name)
                    .createDate(date)
                    .changeDate(date)
                    .build();
            DBHelperSQLite.get(((Context) getView()).getApplicationContext()).putDebit(debit);
            CommonUtils.showLoadingDialog((Context) getView());
        } else if (dto instanceof CreditDTO) {
            CreditDTO creditDTO = (CreditDTO) dto;
            Float amount = Float.valueOf(creditDTO.getAmount());
            String name = creditDTO.getName();
            Credit credit = Credit.builder()
                    .arrival(amount)
                    .name(name)
                    .createDate(date)
                    .changeDate(date)
                    .build();
            DBHelperSQLite.get(((Context) getView()).getApplicationContext()).putCredit(credit);
            CommonUtils.showLoadingDialog((Context) getView());
        }
        getView().onBackPressed();
    }

    @Override
    public void cancelButtonPressed() {
        getView().onBackPressed();
    }
}
