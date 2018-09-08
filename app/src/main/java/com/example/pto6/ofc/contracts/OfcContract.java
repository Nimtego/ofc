package com.example.pto6.ofc.contracts;

import com.example.pto6.ofc.model.Credit;
import com.example.pto6.ofc.model.Debit;
import com.example.pto6.ofc.utils.TabType;

import java.util.List;
import java.util.Map;


public interface OfcContract {
    interface Presenter<V extends View> extends Contract.Presenter<V> {
        void tabLayoutSelect(TabType tabType);

        void pushFab();

        void longPushInRV(int number);

        void pushInRV(int number);

        void viewIsReady();

        TabType getState();
    }

    interface View<P extends Presenter> extends Contract.View<P> {
        void setDebitListView(List<? extends Debit> listDebit);

        void setCreditListView(List<? extends Credit> listCredit);

        void clearList();

        void toast(String message);

        void intent(Map<String, String> props, Class nextView);
    }
}
