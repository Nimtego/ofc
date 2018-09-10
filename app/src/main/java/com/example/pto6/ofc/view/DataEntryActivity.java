package com.example.pto6.ofc.view;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.contracts.DataEntryContract;
import com.example.pto6.ofc.dto.UserFinanceDTO;
import com.example.pto6.ofc.view.fragments.DataEntryFragment;

public abstract class DataEntryActivity<T extends UserFinanceDTO> extends BaseView<DataEntryContract.Presenter>
        implements DataEntryContract.View<DataEntryContract.Presenter, T>,
        DataEntryListener {

    private static final String TAG = "DataEntryActivity";
    private TextView cap;
    private DataEntryFragment<? extends T> mFragment;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        cap = findViewById(R.id.cap);
        this.mFragment = getFragment();
        cap.setText(getCapText());
        mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.fragment_form, mFragment);
        mFragmentTransaction.commit();
    }

    @Override
    public DataEntryContract.Presenter supplyPresenter() {
        return OfcApplication
                .getPresenterComponent()
                .getDataEntryPresenter();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        mFragment = null;
        mFragmentTransaction = null;
        super.onDestroy();
    }

    protected abstract DataEntryFragment<? extends T> getFragment();

    protected abstract String getCapText();

    @Override
    public T getFormData() {
        return mFragment.getFormData();
    }

    @Override
    public void onAddPressed() {
        mPresenter.addButtonPressed();
    }

    @Override
    public void onCancelPressed() {
        mPresenter.cancelButtonPressed();
    }
}
