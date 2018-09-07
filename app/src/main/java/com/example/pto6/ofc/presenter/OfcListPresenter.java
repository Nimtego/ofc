package com.example.pto6.ofc.presenter;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.contracts.OfcContract;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.service.AsyncDBHelper;
import com.example.pto6.ofc.service.AsyncDBHelperSQLite;
import com.example.pto6.ofc.utils.TabType;
import com.example.pto6.ofc.view.ViewTable;
import com.example.pto6.ofc.view.ViewTableImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (!tabType.equals(TabType.DATA)) {
            Map<String, String> props = new HashMap<>();
            props.put("TYPE", tabType.toString());
            view.intent(props, viewTable.get("data"));
        } else
            runGraphsView();
    }

    @Override
    public void longPushInRV(int number) {
        view.toast("I in longPushInRV");
        if (tabType.equals(TabType.DEBIT)) {
            dbHelper().removeDebit(
                    mDebitList.get(number),
                    () -> getView().runOnMainThread(this::viewIsReady),
                    err -> getView().runOnMainThread(this::viewIsReady)
            );
        }
        if (tabType.equals(TabType.CREDIT)) {
            dbHelper().removeCredit(
                    mCreditList.get(number),
                    () -> getView().runOnMainThread(this::viewIsReady),
                    err -> getView().runOnMainThread(this::viewIsReady)
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
        if (tabType.equals(TabType.CREDIT)) {
            dbHelper().creditList(
                    list -> {
                        mCreditList = list;
                        getView().runOnMainThread(() -> view.setCreditListView(list));
                    },
                    err -> getView().runOnMainThread(() -> view.toast(err.getMessage()))
            );
        }

        if (tabType.equals(TabType.DEBIT)) {
            dbHelper().debitList(
                    list -> {
                        mDebitList = list;
                        getView().runOnMainThread(() -> view.setDebitListView(list));
                    },
                    err -> getView().runOnMainThread(() -> view.toast(err.getMessage()))
            );
        }

        if (tabType.equals(TabType.DATA)) {
            view.toast("IN PROGRESS");
//            runGraphsView(); todo
            view.clearList();
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
