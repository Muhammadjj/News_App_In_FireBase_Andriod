package org.pk.cas.newsappwithfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Objects.requireNonNull(getSupportActionBar()).hide();
        textView =findViewById(R.id.news_Text_Splash);
        imageView = findViewById(R.id.news_image_Splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginPageActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


        Animation anim = AnimationUtils.loadAnimation(SplashScreen.this,
                R.anim.blink_animation);
        textView.startAnimation(anim);

        Animation animation = AnimationUtils.loadAnimation(SplashScreen.this,
                R.anim.bounce_animation);
        imageView.startAnimation(animation);

    }
}