package com.example.pto6.ofc.view;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;

public interface OfcView extends CommonView{

    void setUserFinance(RecyclerView.Adapter mAdapter);
    RecyclerView getRecyclerView();
    TabLayout getTabLayout();

}
