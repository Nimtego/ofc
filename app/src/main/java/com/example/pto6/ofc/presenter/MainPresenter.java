package com.example.pto6.ofc.presenter;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.pto6.ofc.R;


public class MainPresenter extends AbstractBasePresenter{
    private final String LOG_TAG = "Main presenter";

    @Override
    public void onClick(View view) {
/*        if (view.getId() == R.id.fab) {
            Log.v(LOG_TAG, "Fab push");
            intent();
        }*/
    }

    @Override
    Class getNextActivity() {
        Log.v(LOG_TAG, "Get next activity");
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
