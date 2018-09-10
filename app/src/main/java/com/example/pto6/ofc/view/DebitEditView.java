package com.example.pto6.ofc.view;

import com.example.pto6.ofc.R;
import com.example.pto6.ofc.dto.DebitDTO;
import com.example.pto6.ofc.view.fragments.AddDebitFragment;
import com.example.pto6.ofc.view.fragments.DataEntryFragment;

public class DebitEditView extends DataEntryActivity<DebitDTO> {
    @Override
    protected DataEntryFragment<? extends DebitDTO> getFragment() {
        return new AddDebitFragment();
    }

    @Override
    protected String getCapText() {
        return getResources().getString(R.string.tab_layout_tab_debit);
    }
}
