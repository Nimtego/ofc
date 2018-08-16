package com.example.pto6.ofc;

import android.app.Application;

import com.example.pto6.ofc.component.DBComponent;
import com.example.pto6.ofc.component.DaggerDBComponent;
import com.example.pto6.ofc.component.DaggerPresenterComponent;
import com.example.pto6.ofc.component.PresenterComponent;


public class OfcApplication extends Application {

    private static DBComponent mDBComponent;
    private static PresenterComponent presenterComponent;

    public static DBComponent getDBComponent() {
        return mDBComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDBComponent = DaggerDBComponent.create();
        presenterComponent = DaggerPresenterComponent.create();
    }
    public static PresenterComponent getPresenterComponent() {
        return presenterComponent;
    }
}
