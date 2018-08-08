package com.example.pto6.ofc.view;


import android.support.annotation.StringRes;

import com.example.pto6.ofc.presenter.Presenter;


/**
 * Created by nimtego_loc on 20.03.2018.
 */

public interface CommonView {
    void showLoading();
    void hideLoading();
    void openActivityOnTokenExpire();
    void onError(@StringRes int resId);
    void onError(String message);
    void showMessage(String message);
    void showMessage(@StringRes int resId);
    boolean isNetworkConnected();
    void hideKeyboard();
    Presenter setPresenter();
}
