package com.example.pto6.ofc.contracts;


import com.example.pto6.ofc.dto.UserFinanceDTO;

public interface DataEntryContract {
    
    interface DataPresenter<T extends DataEntryView> extends BaseContract.Presenter<T> {
        void takeDTO(UserFinanceDTO debitDTO);
    }
    interface DataEntryView extends BaseContract.CommonView {
        void onBackPressed();
        UserFinanceDTO getDTO();
    }
}
