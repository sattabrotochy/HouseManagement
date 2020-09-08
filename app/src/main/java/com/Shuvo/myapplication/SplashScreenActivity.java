package com.Shuvo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.Shuvo.myapplication.Authentication.SIngInActivity;

public class SplashScreenActivity extends AppCompatActivity {


    Animation topAnim, bottomAnim;
    ImageView imageView;
    TextView textView1, textView2;
    Context context = SplashScreenActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        topAnim = AnimationUtils.loadAnimation(this, R.anim.up_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.buttom_animation);

        imageView = findViewById(R.id.image);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        imageView.setAnimation(topAnim);
        textView1.setAnimation(bottomAnim);
        textView2.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, SIngInActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);


    }
}