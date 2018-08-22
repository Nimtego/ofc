package com.example.pto6.ofc.presenter;

import com.example.pto6.ofc.dto.UserFinanceDTO;
import com.example.pto6.ofc.view.DataEntryView;


public interface DataPresenter<T extends DataEntryView> extends Presenter<T>{
    void takeDTO(UserFinanceDTO debitDTO);
}
