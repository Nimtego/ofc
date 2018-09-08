package com.example.pto6.ofc.component;

import com.example.pto6.ofc.contracts.DataEntryContract;
import com.example.pto6.ofc.contracts.GraphsContract;
import com.example.pto6.ofc.contracts.OfcContract;
import com.example.pto6.ofc.presenter.DataEntryPresenter;
import com.example.pto6.ofc.presenter.GraphsPresenter;
import com.example.pto6.ofc.presenter.OfcListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Provides
    DataEntryContract.Presenter dataEntryPresenter() {
        return new DataEntryPresenter();
    }

    @Provides
    OfcContract.Presenter ofcListPresenter() {
        return new OfcListPresenter();
    }

    @Provides
    GraphsContract.Presenter graphsPresenter() {
        return new GraphsPresenter();
    }
}
