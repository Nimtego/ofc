package com.example.pto6.ofc.component;

import com.example.pto6.ofc.presenter.AbstractBasePresenter;
import com.example.pto6.ofc.presenter.DataEntryPresenter;
import com.example.pto6.ofc.presenter.OfcListPresenter;
import com.example.pto6.ofc.presenter.Presenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {


    @Provides
    AbstractBasePresenter dataEntryPresenter() {
        return new DataEntryPresenter();
    }

    @Provides
    AbstractBasePresenter ofcListPresenter() {
        return new OfcListPresenter();
    }
}
