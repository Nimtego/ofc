package com.example.pto6.ofc.contracts;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;

import java.util.List;


public interface OfcContract {
    interface Presenter<V extends View> extends Contract.Presenter<V> {
        void tabLayoutSelect(int numberTab);
        void pushFab();
        void longPushInRV(int number);
        void pushInRV(int number);
        void viewIsReady();
    }

    interface View<P extends Presenter> extends Contract.View<P> {
        void setDebitListView(List<? extends Debit> listDebit);
        void setCredittListView(List<? extends Credit> listCredit);
    }
}
