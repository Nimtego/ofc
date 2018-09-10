package com.example.pto6.ofc.presenter;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.contracts.OfcContract;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.service.AsyncDBHelper;
import com.example.pto6.ofc.service.AsyncDBHelperSQLite;
import com.example.pto6.ofc.utils.CommonUtils;
import com.example.pto6.ofc.utils.TabType;
import com.example.pto6.ofc.view.ViewTable;
import com.example.pto6.ofc.view.ViewTableImpl;

import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OfcListPresenter extends BasePresenter<OfcContract.View>
        implements OfcContract.Presenter<OfcContract.View> {

    private static final String TAG = "OfcListPresenter";
    //todo DI
    private final ViewTable viewTable = new ViewTableImpl();
    private TabType tabType = TabType.DEBIT;
    private List<Debit> mDebitList;
    private List<Credit> mCreditList;

    private AsyncDBHelper dbHelper() {
        return AsyncDBHelperSQLite.get(OfcApplication.getAppContext());
    }


    public void tabLayoutSelect(TabType tabType) {
        this.tabType = tabType;
        viewIsReady();
    }

    @Override
    public void pushFab() {
        if (tabType.equals(TabType.DEBIT)) {
            view.intent(Collections.emptyMap(), viewTable.get("debit"));
        } else if (tabType.equals(TabType.CREDIT)) {
            view.intent(Collections.emptyMap(), viewTable.get("credit"));
        } else
            runGraphsView();
    }

    @Override
    public void longPushInRV(int number) {
        view.toast("I in longPushInRV");
        ProgressDialog progressDialog = CommonUtils.showLoadingDialog((Context) getView());
        if (tabType.equals(TabType.DEBIT)) {
            dbHelper().removeDebit(
                    mDebitList.get(number),
                    () -> getView().runOnMainThread(() -> {
                        progressDialog.cancel();
                        viewIsReady();
                    }),
                    err -> getView().runOnMainThread(() -> {
                        progressDialog.cancel();
                        viewIsReady();
                    })
            );
        }
        if (tabType.equals(TabType.CREDIT)) {
            dbHelper().removeCredit(
                    mCreditList.get(number),
                    () -> getView().runOnMainThread(() -> {
                        progressDialog.cancel();
                        viewIsReady();
                    }),
                    err -> getView().runOnMainThread(() -> {
                        progressDialog.cancel();
                        viewIsReady();
                    })
            );
        }
    }

    @Override
    public void pushInRV(int number) {
        view.toast("I in pushInRV");
        // TODO: 31.08.2018
    }

    @Override
    public void viewIsReady() {
        ProgressDialog progressDialog = CommonUtils.showLoadingDialog((Context) getView());
        if (tabType.equals(TabType.CREDIT)) {
            dbHelper().creditList(
                    list -> {
                        mCreditList = list;
                        getView().runOnMainThread(() -> {
                            progressDialog.cancel();
                            view.setCreditListView(list);
                        });
                    },
                    err -> getView().runOnMainThread(() -> {
                        progressDialog.cancel();
                        view.toast(err.getMessage());
                    })
            );
        }

        if (tabType.equals(TabType.DEBIT)) {
            dbHelper().debitList(
                    list -> {
                        mDebitList = list;
                        getView().runOnMainThread(() -> {
                            progressDialog.cancel();
                            view.setDebitListView(list);
                        });
                    },
                    err -> getView().runOnMainThread(() -> {
                        progressDialog.cancel();
                        view.toast(err.getMessage());
                    })
            );
        }

        if (tabType.equals(TabType.DATA)) {
            view.toast("IN PROGRESS");
//            runGraphsView(); todo
            view.clearList();
            progressDialog.cancel();
        }
    }

    @Override
    public TabType getState() {
        return tabType;
    }

    private void runGraphsView() {
        view.intent(Collections.emptyMap(), viewTable.get("graphs"));
    }

}
