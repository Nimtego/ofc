package com.example.pto6.ofc.presenter;

import android.view.View;

import com.example.pto6.ofc.view.AbstractView;

public interface Presenter<T extends AbstractView> extends View.OnClickListener{
    void attach(T commonView);
    void detach();
    void intent();

}
