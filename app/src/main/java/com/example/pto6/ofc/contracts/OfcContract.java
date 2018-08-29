package com.example.pto6.ofc.contracts;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;


public interface OfcContract {
    interface OfcPresenter<T extends OfcView> extends BaseContract.Presenter<T> {
    }

    interface OfcView extends BaseContract.CommonView {
        void setUserFinance(RecyclerView.Adapter mAdapter);
        RecyclerView getRecyclerView();
        TabLayout getTabLayout();

    }
}
