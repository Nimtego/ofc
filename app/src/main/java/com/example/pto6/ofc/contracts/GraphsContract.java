package com.example.pto6.ofc.contracts;


public interface GraphsContract {

    interface Presenter<V extends GraphsContract.View> extends Contract.Presenter<V> {
    }

    interface View<P extends GraphsContract.Presenter> extends Contract.View<P> {
    }
}
