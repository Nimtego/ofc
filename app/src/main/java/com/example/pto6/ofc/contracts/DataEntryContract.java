package com.example.pto6.ofc.contracts;


import com.example.pto6.ofc.dto.UserFinanceDTO;

public interface DataEntryContract {
    
    interface Presenter<V extends View> extends Contract.Presenter<V> {
        void takeDTO(UserFinanceDTO debitDTO);
    }
    interface View<P extends Presenter> extends Contract.View<P> {
        void onBackPressed();
        UserFinanceDTO getDTO();
    }
}
