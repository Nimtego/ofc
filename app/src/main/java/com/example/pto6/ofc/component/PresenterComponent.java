package com.example.pto6.ofc.component;

import com.example.pto6.ofc.presenter.DataEntryPresenter;
import com.example.pto6.ofc.presenter.DataPresenter;
import com.example.pto6.ofc.presenter.OfcListPresenter;
import com.example.pto6.ofc.presenter.OfcPresenter;
import com.example.pto6.ofc.view.OfcView;

import dagger.Component;

@Component(modules = PresenterModule.class)
public interface PresenterComponent {
    OfcPresenter getOfcListPresenter();
    DataPresenter getDataEntryPresenter();
}
