package com.example.pto6.ofc.presenter;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.pto6.ofc.view.CommonView;

public interface Presenter<T extends CommonView> extends View.OnClickListener,
                                                TabLayout.OnTabSelectedListener,
                                                RecyclerView.OnItemTouchListener {

    void attach(T commonView);
    void detach();
    void intent();
    void intent(String key, String value);
    void viewReady();
}
