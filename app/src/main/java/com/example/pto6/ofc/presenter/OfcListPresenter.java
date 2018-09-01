package com.example.pto6.ofc.presenter;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.contracts.OfcContract;
import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.service.DBHelper;
import com.example.pto6.ofc.service.DBHelperSQLite;
import com.example.pto6.ofc.utils.TabType;
import com.example.pto6.ofc.view.DataEntryActivity;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OfcListPresenter extends BasePresenter<OfcContract.View>
        implements OfcContract.Presenter<OfcContract.View> {

    private static final String TAG = "OfcListPresenter";
    private TabType tabType;
    private List<Debit> mDebitList;
    private List<Credit> mCreditList;

    private DBHelper dbHelper() {
        return DBHelperSQLite.get(OfcApplication.getAppContext());
    }


    public void tabLayoutSelect(TabType tabType) {
        if (!tabType.equals(this.tabType)) {
            this.tabType = tabType;
        }
        viewIsReady();
    }

    @Override
    public void pushFab() {
        view.intent(tabType.toString());
    }

    @Override
    public void longPushInRV(int number) {
        if (tabType.equals(TabType.DEBIT)) {
            dbHelper().removeByNameDebit(mDebitList.get(number).getName());
        }
        if (tabType.equals(TabType.CREDIT)) {
            dbHelper().removeByNameDebit(mCreditList.get(number).getName());
        }
    }

    @Override
    public void pushInRV(int number) {
        // TODO: 31.08.2018
    }

    @Override
    public void viewIsReady() {
        if (tabType == null) {
            tabType = view.getState();
            viewIsReady();
        }
        if (tabType.equals(TabType.CREDIT))
            this.mCreditList = dbHelper().creditList();
        view.setCreditListView(mCreditList);

        if (tabType.equals(TabType.DEBIT))
            this.mDebitList = dbHelper().debitList();
        view.setDebitListView(mDebitList);

        if (tabType.equals(TabType.DATA)) {
            view.toast("IN PROGRESS");
            // TODO: 31.08.2018
        }
    }

    @Override
    public Class getNextActivity() {
        return DataEntryActivity.class;
    }
}
