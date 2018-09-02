package com.example.pto6.ofc.view.fragments;

import com.example.pto6.ofc.dto.UserFinanceDTO;
import com.example.pto6.ofc.view.DataEntryListener;

public abstract class DataEntryFragment extends BaseFragment<DataEntryListener> {
    public abstract UserFinanceDTO getFormData();
}
