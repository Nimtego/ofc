package com.example.pto6.ofc.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.pto6.ofc.contracts.BaseContract;
import com.example.pto6.ofc.contracts.OfcContract;
import com.example.pto6.ofc.view.CommonView;
import com.example.pto6.ofc.view.toast.SimpleToastAlarm;
import com.example.pto6.ofc.view.toast.ToastAlarm;


public abstract class AbstractBasePresenter<V extends BaseContract.CommonView> implements
        BaseContract.Presenter<V> {
    private V commonView;
    private ToastAlarm toastAlarm;


    @Override
    public void attach(V commonView) {
        this.commonView = commonView;
        this.toastAlarm = new SimpleToastAlarm(getContext());
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

    @Override
    public void intent(String key, String value) {
        Intent intent = new Intent((Activity) commonView, getNextActivity());
        intent.putExtra(key, value);
        ((Activity) commonView).startActivity(intent);
    }

    protected V getView() {
        return commonView;
    }
    protected Context getContext() {
        return (Context) commonView;
    }

    abstract Class getNextActivity();
}
