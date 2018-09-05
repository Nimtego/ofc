package com.example.pto6.ofc.contracts;



import java.util.Map;

public interface GraphsContract {

    interface Presenter<V extends GraphsContract.View> extends Contract.Presenter<V> {
        void viewReady();
    }

    interface View<P extends GraphsContract.Presenter> extends Contract.View<P> {
        void startAction(Map<DynamicData, Runnable> runnableMap, int delay);
        void updateCare(String value);
        void updateParish(String value);
        void updateIncome(String value);
    }
}
