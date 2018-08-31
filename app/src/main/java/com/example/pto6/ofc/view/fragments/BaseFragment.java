package com.example.pto6.ofc.view.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.View;

import com.example.pto6.ofc.contracts.Contract;
import com.example.pto6.ofc.dto.UserFinanceDTO;
import com.example.pto6.ofc.view.BaseView;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements Contract.View{


    private BaseView mAbstractView;
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
        if (context instanceof BaseView) {
            this.mAbstractView = (BaseView) context;
           // activity.onFragmentAttached();
        }
    }

/*    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }*/

/*    @Override
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
    }*/

    @Override
    public void onDetach() {
        mAbstractView = null;
        super.onDetach();
    }

/*    @Override
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
    }*/

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

/*    @Override
    public Presenter setPresenter() {
        return null;
    }*/
    public abstract UserFinanceDTO getDTO();

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }


}