package com.example.pto6.ofc.presenter;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.contracts.DynamicData;
import com.example.pto6.ofc.contracts.GraphsContract;
import com.example.pto6.ofc.service.DBHelper;

import java.util.HashMap;
import java.util.Map;


public class GraphsPresenter extends BasePresenter<GraphsContract.View>
        implements GraphsContract.Presenter<GraphsContract.View>{

    private float parish;
    private float care;
    private float income;

    @Override
    public void viewReady() {
            DBHelper dbHelper = OfcApplication.getDBComponent().getDBHelper();
            dbHelper.debitList().forEach(v -> parish += v.getArrival());
            dbHelper.creditList().forEach(v -> care += v.getArrival());
            income = parish - care;


        view.startAction(getMap(), 100);


    }//2592000  576000

    private Map<DynamicData, Runnable> getMap() {
        Map<DynamicData, Runnable> map = new HashMap<>();
        map.put(DynamicData.CARE,() -> view.updateCare(String.valueOf(100/*care / 5F*/)));
        map.put(DynamicData.PARISH,() -> view.updateParish(String.valueOf(100)));
        map.put(DynamicData.INCOME,() -> view.updateIncome(String.valueOf(100)));
        return map;
    }
}
