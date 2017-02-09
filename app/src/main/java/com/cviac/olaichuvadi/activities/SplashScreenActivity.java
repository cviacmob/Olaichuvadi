package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.cviac.olaichuvadi.utilities.Prefs;

public class SplashScreenActivity extends AppCompatActivity {

    public static String str_reg_test;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*setContentView(R.layout.activity_splash_screen);*/

        str_reg_test = Prefs.getString("isregistered", null);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (str_reg_test != null
                        && !str_reg_test.toString().trim().equals("")) {
                    Intent log = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(log);
                    finish();
                } else {
                    Intent send = new Intent(getApplicationContext(),
                            LoginActivity.class);
                    startActivity(send);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}