package com.example.pto6.ofc.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.pto6.ofc.R;



public class OfcListActivity extends AppCompatActivity {

    private TextView textHello;
    private ImageView imageLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofc_list);
        textHello = findViewById(R.id.text_hello);
        imageLogo = findViewById(R.id.logo);
        actionText();
    }
    private void actionText() {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(600);
        anim.setStartOffset(10);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        //textHello.startAnimation(anim);
        imageLogo.startAnimation(anim);
    }
}
