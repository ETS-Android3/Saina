package com.dennis.saina;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(2 * 1000);

                    // After 5 seconds redirect to another intent
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();

                    //Remove activity
                    finish();
                } catch (Exception e) {
                    Log.i("Start", "Something went wrong");
                }
            }
        };
        // start thread
        background.start();

    }
}