package com.example.pto6.ofc.component;

import com.example.pto6.ofc.presenter.DataPresenter;
import com.example.pto6.ofc.presenter.OfcPresenter;

import dagger.Component;

@Component(modules = PresenterModule.class)
public interface PresenterComponent {
    OfcPresenter getOfcListPresenter();

    DataPresenter getDataEntryPresenter();
}
