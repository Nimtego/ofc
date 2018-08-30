package com.example.pto6.ofc.contracts;


import com.example.pto6.ofc.dto.UserFinanceDTO;

public interface DataEntryContract {
    
    interface DataPresenter<V extends DataEntryView> extends Contract.Presenter<V> {
        void takeDTO(UserFinanceDTO debitDTO);
    }
    interface DataEntryView<P extends DataPresenter> extends Contract.View<P> {
        void onBackPressed();
        UserFinanceDTO getDTO();
    }
}
