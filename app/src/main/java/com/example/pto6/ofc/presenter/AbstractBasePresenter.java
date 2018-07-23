package com.example.pto6.ofc.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.pto6.ofc.view.AbstractView;


public abstract class AbstractBasePresenter<T extends AbstractView> implements Presenter<T> {
    protected T commonView;
    protected ToastAlarm toastAlarm;


    @Override
    public void attach(T commonView) {
        this.commonView = commonView;
        this.toastAlarm = new SimpleToastAlarm((Context) commonView);
    }

    @Override
    public void detach() {
        commonView = null;
        toastAlarm.destroy();
    }

    @Override
    public void intent() {
        Intent intent = new Intent((Activity) commonView, getNextActivity());
        ((Activity) commonView).startActivity(intent);
    }

    abstract Class getNextActivity();
}
