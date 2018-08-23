package com.example.pto6.ofc.view;

import com.example.pto6.ofc.dto.UserFinanceDTO;

public interface DataEntryView extends CommonView{
    void onBackPressed();
    UserFinanceDTO getDTO();
}
