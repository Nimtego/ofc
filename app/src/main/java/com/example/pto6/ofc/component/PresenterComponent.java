package com.example.pto6.ofc.component;

import com.example.pto6.ofc.presenter.AbstractBasePresenter;
import com.example.pto6.ofc.presenter.DataEntryPresenter;
import com.example.pto6.ofc.presenter.OfcListPresenter;

import dagger.Component;

@Component(modules = PresenterModule.class)
public interface PresenterComponent {
    OfcListPresenter getOfcListPresenter();
    DataEntryPresenter getDataEntryPresenter();
}
