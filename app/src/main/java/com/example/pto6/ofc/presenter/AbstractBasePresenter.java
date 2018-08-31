package com.example.pto6.ofc.presenter;

import android.content.Context;

import com.example.pto6.ofc.contracts.Contract;
import com.example.pto6.ofc.view.toast.SimpleToastAlarm;
import com.example.pto6.ofc.view.toast.ToastAlarm;


public abstract class AbstractBasePresenter<V extends Contract.View> implements
        Contract.Presenter<V> {
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

/*    @Override
    public void intent() {
        Intent intent = new Intent((Activity) commonView, getNextActivity());
        ((Activity) commonView).startActivity(intent);
    }*/

/*    @Override
    public void intent(String key, String value) {
        Intent intent = new Intent((Activity) commonView, getNextActivity());
        intent.putExtra(key, value);
        ((Activity) commonView).startActivity(intent);
    }*/

    public V getView() {
        return commonView;
    }
    public Context getContext() {
        return (Context) commonView;
    }

}
