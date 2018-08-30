package com.example.pto6.ofc.view;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.pto6.ofc.contracts.BaseContract;
import com.example.pto6.ofc.contracts.OfcContract;
import butterknife.Unbinder;


public abstract class AbstractView extends AppCompatActivity implements BaseContract.CommonView {

    protected BaseContract.Presenter mPresenter;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = setPresenter();
        mPresenter.attach(this);
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    protected void onDestroy() {
        mPresenter.detach();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }
}
