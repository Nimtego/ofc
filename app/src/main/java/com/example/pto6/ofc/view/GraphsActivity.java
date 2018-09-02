package com.example.pto6.ofc.view;

import android.os.Bundle;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.contracts.GraphsContract;

public class GraphsActivity extends BaseView<GraphsContract.Presenter>
        implements GraphsContract.View<GraphsContract.Presenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
    }

    @Override
    public GraphsContract.Presenter supplyPresenter() {
        return OfcApplication.getPresenterComponent().getGraphsPresenter();
    }
}
