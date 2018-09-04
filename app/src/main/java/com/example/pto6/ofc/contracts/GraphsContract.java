package com.example.pto6.ofc.contracts;


import com.example.pto6.ofc.utils.Pair;

import java.util.Map;

public interface GraphsContract {

    interface Presenter<V extends GraphsContract.View> extends Contract.Presenter<V> {
        void viewReady();
    }

    interface View<P extends GraphsContract.Presenter> extends Contract.View<P> {
        void setData(Map<DynamicData, Float> data);
    }

}
