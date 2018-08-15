package com.example.pto6.ofc.component;

import com.example.pto6.ofc.presenter.DataEntryPresenter;
import com.example.pto6.ofc.presenter.DataPresenter;
import com.example.pto6.ofc.presenter.OfcListPresenter;
import com.example.pto6.ofc.presenter.OfcPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    DataPresenter dataEntryPresenter() {
        return new DataEntryPresenter();
    }
    @Provides
    OfcPresenter ofcListPresenter() {
        return new OfcListPresenter();
    }
}
