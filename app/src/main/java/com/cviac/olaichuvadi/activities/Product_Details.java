package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cviac.olaichuvadi.R;

public class Product_Details extends AppCompatActivity {

    Button crt,buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);
        setTitle("Product Details");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        crt = (Button) findViewById(R.id.cartbtn);
        buy = (Button) findViewById(R.id.buybtn);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buyitm=new Intent(Product_Details.this,PaymentActivity.class);
                startActivity(buyitm);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
