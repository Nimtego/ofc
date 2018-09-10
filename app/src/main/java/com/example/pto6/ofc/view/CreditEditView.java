package com.example.pto6.ofc.view;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.dto.CreditDTO;
import com.example.pto6.ofc.view.fragments.AddCreditFragment;
import com.example.pto6.ofc.view.fragments.DataEntryFragment;

public class CreditEditView extends DataEntryActivity<CreditDTO> {
    @Override
    protected DataEntryFragment<? extends CreditDTO> getFragment() {
        return new AddCreditFragment();
    }

    @Override
    protected String getCapText() {
        return getResources().getString(R.string.tab_layout_tab_credit);
    }
}
