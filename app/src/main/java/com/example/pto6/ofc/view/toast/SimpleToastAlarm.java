package com.example.pto6.ofc.view.toast;

import android.content.Context;
import android.widget.Toast;

public class SimpleToastAlarm implements ToastAlarm {

    private Context mContext;

    public SimpleToastAlarm(Context commonView) {
        mContext = commonView;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void message(String message) {
        Toast toast = Toast.makeText(mContext.getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
