package com.example.pto6.ofc.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.contracts.DynamicData;
import com.example.pto6.ofc.contracts.GraphsContract;

import java.text.DecimalFormat;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphsActivity extends BaseView<GraphsContract.Presenter>
        implements GraphsContract.View<GraphsContract.Presenter> {
    @BindView(R.id.edit_text_parish)
    TextView parishSpeed;
    @BindView(R.id.edit_text_care)
    TextView careSpeed;
    @BindView(R.id.edit_text_income)
    TextView incomeSpeed;
    @BindView(R.id.edit_text_parish_speed)
    TextView parish;
    @BindView(R.id.edit_text_care_speed)
    TextView care;
    @BindView(R.id.edit_text_income_speed)
    TextView income;
    private float p = 0;
    private float c = 0;
    private float i = 0;

    private boolean firstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        if (savedInstanceState == null)
            firstStart = true;
        ButterKnife.bind(this);
        mPresenter.viewReady();
    }

    @Override
    public GraphsContract.Presenter supplyPresenter() {
        return OfcApplication.getPresenterComponent().getGraphsPresenter();
    }

    @Override
    public void setData(Map<DynamicData, Float> data) {
        if (firstStart) {
/*            parishSpeed.setText(String.valueOf(data.get(DynamicData.PARISH)));
            careSpeed.setText(String.valueOf(data.get(DynamicData.CARE)));
            incomeSpeed.setText(String.valueOf(data.get(DynamicData.INCOME)));*/
            parishSpeed.setText(String.valueOf(0.14));
            careSpeed.setText(String.valueOf(0.07));
            incomeSpeed.setText(String.valueOf(0.14-0.07));
        }
        DecimalFormat df = new DecimalFormat("#.##");
        care.post(new Runnable() {
            @Override
            public void run() {
                c += Float.valueOf(String.valueOf(careSpeed.getText()));
                care.setText(String.valueOf(df.format(c)));
                new Handler().postDelayed(this, 200);
            }
        });
        parish.post(new Runnable() {
            @Override
            public void run() {
                p += Float.valueOf(String.valueOf(parishSpeed.getText()));
                parish.setText(String.valueOf(df.format(p)));
                new Handler().postDelayed(this, 200);
            }
        });
        income.post(new Runnable() {
            @Override
            public void run() {
                i += Float.valueOf(String.valueOf(incomeSpeed.getText()));
                income.setText(String.valueOf(df.format(i)));
                new Handler().postDelayed(this, 200);
            }
        });
    }
}
