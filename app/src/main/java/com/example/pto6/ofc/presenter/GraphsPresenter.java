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
    private boolean initData;


    @Override
    public Class getNextActivity() {
        return null;
    }

    @Override
    public void viewReady() {
        if (!initData) {
            DBHelper dbHelper = OfcApplication.getDBComponent().getDBHelper();
            dbHelper.debitList().forEach(v -> parish += v.getArrival());
            dbHelper.creditList().forEach(v -> care += v.getArrival());
            income = parish - care;
            initData = true;
        }
        view.setData(getMap());


    }//2592000  576000

    private Map<DynamicData, Float> getMap() {
        Map<DynamicData, Float> map = new HashMap<>();
        map.put(DynamicData.CARE, care / 576000F);
        map.put(DynamicData.PARISH, parish / 576000F);
        map.put(DynamicData.INCOME, income / 576000F);
        return map;
    }
}
