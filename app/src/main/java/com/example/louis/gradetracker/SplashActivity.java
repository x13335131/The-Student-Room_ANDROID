package com.example.louis.gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private static final int DELAY_MILLISECONDS = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();

        ImageView imageView = (ImageView) findViewById(R.id.aimg);//image produced on fade
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);//loading fade in annimation
        loadAnimation.setDuration(5000);//lasts 5000 miliseconds
        imageView.startAnimation(loadAnimation);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() { //delay content
                Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, DELAY_MILLISECONDS);
    }
}
