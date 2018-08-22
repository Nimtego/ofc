package com.example.pto6.ofc.view.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

import com.example.pto6.ofc.dto.UserFinanceDTO;
import com.example.pto6.ofc.presenter.Presenter;
import com.example.pto6.ofc.utils.CommonUtils;
import com.example.pto6.ofc.view.AbstractView;
import com.example.pto6.ofc.view.CommonView;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements CommonView{


    private AbstractView mAbstractView;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUp(view);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AbstractView) {
            AbstractView activity = (AbstractView) context;
            this.mAbstractView = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {
        if (mAbstractView != null) {
            mAbstractView.onError(message);
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        if (mAbstractView != null) {
            mAbstractView.onError(resId);
        }
    }

    @Override
    public void showMessage(String message) {
        if (mAbstractView != null) {
            mAbstractView.showMessage(message);
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        if (mAbstractView != null) {
            mAbstractView.showMessage(resId);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mAbstractView != null) {
            return mAbstractView.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void onDetach() {
        mAbstractView = null;
        super.onDetach();
    }

    @Override
    public void hideKeyboard() {
        if (mAbstractView != null) {
            mAbstractView.hideKeyboard();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        if (mAbstractView != null) {
            mAbstractView.openActivityOnTokenExpire();
        }
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    protected abstract void setUp(View view);

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public Presenter setPresenter() {
        return null;
    }
    public abstract UserFinanceDTO getDTO();

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }


}