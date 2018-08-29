package com.example.pto6.ofc.contracts;

import android.support.annotation.StringRes;


public interface BaseContract {
    interface Presenter<V extends CommonView> {

        void attach(V commonView);

        void detach();

        void intent();

        void intent(String key, String value);

        void viewReady();
    }

    interface CommonView {
        void showLoading();
        void hideLoading();
        void openActivityOnTokenExpire();
        void onError(@StringRes int resId);
        void onError(String message);
        void showMessage(String message);
        void showMessage(@StringRes int resId);
        void hideKeyboard();
        Presenter setPresenter();
    }
}
