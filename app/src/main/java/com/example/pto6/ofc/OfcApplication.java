package com.example.pto6.ofc;

import android.app.Application;

import com.example.pto6.ofc.component.DBcomponent;
import com.example.pto6.ofc.component.DaggerDBcomponent;
import com.example.pto6.ofc.component.DaggerPresenterComponent;
import com.example.pto6.ofc.component.PresenterComponent;
import com.example.pto6.ofc.presenter.DataEntryPresenter;


public class OfcApplication extends Application {

    private static DBcomponent mDBcomponent;
    private static PresenterComponent presenterComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mDBcomponent = DaggerDBcomponent.create();
        presenterComponent = DaggerPresenterComponent.create();
    }

    public static DBcomponent getDBcomponent() {
        return mDBcomponent;
    }
    public static PresenterComponent getPresenterComponent() {
        return presenterComponent;
    }
}
