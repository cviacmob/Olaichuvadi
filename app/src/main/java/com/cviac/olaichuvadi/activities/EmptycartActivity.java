package com.cviac.olaichuvadi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cviac.olaichuvadi.R;

public class EmptycartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emptycart);

        setTitle("Your Cart is Empty");
    }
}