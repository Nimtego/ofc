package com.example.pto6.ofc.contracts;



public interface BaseContract {
    interface Presenter<V extends CommonView> {
        void attach(V commonView);
        void detach();
        void intent();
        void intent(String key, String value);
    }

    interface CommonView {
        Presenter setPresenter();
    }
}
