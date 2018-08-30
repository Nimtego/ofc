package com.example.pto6.ofc.component;

import com.example.pto6.ofc.contracts.DataEntryContract;
import com.example.pto6.ofc.contracts.OfcContract;

import dagger.Component;

@Component(modules = PresenterModule.class)
public interface PresenterComponent {
    OfcContract.OfcPresenter getOfcListPresenter();

    DataEntryContract.DataPresenter getDataEntryPresenter();
}
