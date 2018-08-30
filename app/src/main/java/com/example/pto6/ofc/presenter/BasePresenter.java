package com.example.pto6.ofc.presenter;

import com.example.pto6.ofc.contracts.Contract;
import com.example.pto6.ofc.view.BaseView;

public abstract class BasePresenter<V extends BaseView>
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
}
