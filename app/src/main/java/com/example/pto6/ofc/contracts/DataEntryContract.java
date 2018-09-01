package com.example.pto6.ofc.contracts;


import com.example.pto6.ofc.dto.UserFinanceDTO;

public interface DataEntryContract {

    interface Presenter<V extends View> extends Contract.Presenter<V> {
        void addButtonPressed();

        void cancelButtonPressed();
    }

    interface View<P extends Presenter> extends Contract.View<P> {
        void onBackPressed();

        UserFinanceDTO getFormData();
    }
}
