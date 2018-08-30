package com.example.pto6.ofc.contracts;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;

import java.util.List;


public interface OfcContract {
    interface OfcPresenter<T extends BaseContract.CommonView> extends BaseContract.Presenter<T> {
        void tabLayoutSelect(int numberTab);
        void pushFab();
        void longPushInRV(int number);
        void pushInRV(int number);
    }

    interface OfcView extends BaseContract.CommonView {
        void setDebitListView(List<? extends Debit> listDebit);
        void setCredittListView(List<? extends Credit> listCredit);

    }
}
