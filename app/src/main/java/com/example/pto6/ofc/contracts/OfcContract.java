package com.example.pto6.ofc.contracts;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;

import java.util.List;


public interface OfcContract {
    interface OfcPresenter<V extends Contract.View> extends Contract.Presenter<V> {
        void tabLayoutSelect(int numberTab);
        void pushFab();
        void longPushInRV(int number);
        void pushInRV(int number);
        void viewIsReady();
    }

    interface OfcView<P extends OfcPresenter> extends Contract.View<P> {
        void setDebitListView(List<? extends Debit> listDebit);
        void setCredittListView(List<? extends Credit> listCredit);
    }
}
