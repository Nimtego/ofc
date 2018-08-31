package com.example.pto6.ofc.presenter;

import com.example.pto6.ofc.contracts.Contract;

public abstract class BasePresenter<V extends Contract.View>
        implements Contract.Presenter<V> {
    protected V view;

    @Override
    public void attach(V view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public V getView() {
        return view;
    }

    abstract Class getNextActivity();
}
