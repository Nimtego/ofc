package com.example.pto6.ofc.contracts;

public interface Contract {

    interface Presenter<V extends Contract.View> {

        void attach(V view);

        void detach();

        V getView();

        Class<?> getNextActivity();

    }

    interface View<P extends Contract.Presenter> {

        P supplyPresenter();
    }
}
