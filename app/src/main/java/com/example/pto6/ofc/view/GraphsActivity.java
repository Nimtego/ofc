package com.example.pto6.ofc.view;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.example.pto6.ofc.OfcApplication;
import com.example.pto6.ofc.R;
import com.example.pto6.ofc.contracts.DynamicData;
import com.example.pto6.ofc.contracts.GraphsContract;

import java.util.Map;
import java.util.stream.Stream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphsActivity extends BaseView<GraphsContract.Presenter>
        implements GraphsContract.View<GraphsContract.Presenter> {
    @BindView(R.id.edit_text_parish)
    TextView parish;
    @BindView(R.id.edit_text_care)
    TextView care;
    @BindView(R.id.edit_text_income)
    TextView income;

    TextSwitcher parishSpeed;
    TextSwitcher careSpeed;
    TextSwitcher incomeSpeed;
    private float p = 0;
    private float c = 0;
    private float i = 0;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        if (savedInstanceState == null)
        ButterKnife.bind(this);
        parishSpeed = findViewById(R.id.edit_text_parish_speed);
        careSpeed = findViewById(R.id.edit_text_care_speed);
        incomeSpeed = findViewById(R.id.edit_text_income_speed);
        mHandler = new Handler();

        Animation inAnimation = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation outAnimation = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);

        Stream.of(parishSpeed, careSpeed, incomeSpeed).peek(v -> v.setFactory(() -> {
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
                textView.setTextSize(16);
                textView.setTextColor(Color.RED);
                return textView;
        })).peek(v -> v.setInAnimation(inAnimation)).forEach(v -> v.setOutAnimation(outAnimation));


        mPresenter.viewReady();
    }

    @Override
    public GraphsContract.Presenter supplyPresenter() {
        return OfcApplication.getPresenterComponent().getGraphsPresenter();
    }

    @Override
    public void startAction(Map<DynamicData, Runnable> runnableMap, int delay) {
        mHandler.postDelayed(runnableMap.get(DynamicData.CARE), delay);
      //  mHandler.postDelayed(runnableMap.get(DynamicData.PARISH), delay);
      //  mHandler.postDelayed(runnableMap.get(DynamicData.INCOME), delay);
    }
    @Override
    public void updateCare(String value) {
        c += Float.valueOf(value);
        careSpeed.setText(String.valueOf(c));
    }
    @Override
    public void updateParish(String value) {
        p += Float.valueOf(value);
        parishSpeed.setText(String.valueOf(p));
    }
    @Override
    public void updateIncome(String value) {
        i += Float.valueOf(value);
        incomeSpeed.setText(String.valueOf(i)); }
}
