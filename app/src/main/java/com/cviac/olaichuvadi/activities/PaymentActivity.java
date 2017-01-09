package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.CartAdapter;
import com.cviac.olaichuvadi.datamodels.CartInfo;
import com.cviac.olaichuvadi.utilities.NonScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PaymentActivity extends AppCompatActivity {

    List<CartInfo> cartitms;
    Button pay;

    @InjectView(R.id.crtitms)
    NonScrollListView nonScrollListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle("Payment");
        loadcrtitms();
        ButterKnife.inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CartAdapter adapter = new CartAdapter(PaymentActivity.this, cartitms);
        nonScrollListView = (NonScrollListView) findViewById(R.id.crtitms);
        nonScrollListView.setAdapter(adapter);
        pay = (Button) findViewById(R.id.paybutn);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentActivity.this, "Payment", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void loadcrtitms() {
        cartitms = new ArrayList<>();

        CartInfo c1 = new CartInfo(R.mipmap.ic, "Title 1", "Desc 1", "$ 50");
        cartitms.add(c1);
        CartInfo c2 = new CartInfo(R.mipmap.ic, "Title 2", "Desc 2", "$ 65");
        cartitms.add(c2);
        CartInfo c3 = new CartInfo(R.mipmap.ic, "Title 3", "Desc 3", "$ 70");
        cartitms.add(c3);
        CartInfo c4 = new CartInfo(R.mipmap.ic, "Title 4", "Desc 4", "$ 60");
        cartitms.add(c4);
        CartInfo c5 = new CartInfo(R.mipmap.ic, "Title 5", "Desc 5", "$ 55");
        cartitms.add(c5);
        CartInfo c6 = new CartInfo(R.mipmap.ic, "Title 6", "Desc 6", "$ 45");
        cartitms.add(c6);

    }
}