package com.example.pto6.ofc.contracts;

import android.support.annotation.StringRes;


public interface BaseContract {
    interface Presenter<V extends CommonView> extends Contract.Presenter<V> {

        void intent();

        void intent(String key, String value);

        void viewReady();
    }

    interface CommonView<P extends BaseContract.Presenter> extends Contract.View<P> {

        void showLoading();

        void hideLoading();

        void openActivityOnTokenExpire();

        void onError(@StringRes int resId);

        void onError(String message);

        void showMessage(String message);

        void showMessage(@StringRes int resId);

        void hideKeyboard();
    }
}
