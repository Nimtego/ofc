package com.example.pto6.ofc.view;

import com.example.pto6.ofc.dto.DebitDTO;

public interface DataEntryView extends CommonView{
    void onBackPressed();
    DebitDTO getDTO();
}
