package com.example.pto6.ofc.contracts;

import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public interface BaseContract {
    interface Presenter<T extends CommonView> extends View.OnClickListener,
            TabLayout.OnTabSelectedListener,
            RecyclerView.OnItemTouchListener {

        void attach(T commonView);

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
        boolean isNetworkConnected();
        void hideKeyboard();
        Presenter setPresenter();
    }
}
