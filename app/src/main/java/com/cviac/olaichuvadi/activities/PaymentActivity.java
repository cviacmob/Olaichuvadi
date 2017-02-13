package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.CartItemAdapter;
import com.cviac.olaichuvadi.datamodels.ProductCartInfo;
import com.cviac.olaichuvadi.utilities.NonScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PaymentActivity extends AppCompatActivity {

    List<ProductCartInfo> cartProducts;
    Button pay;

    @InjectView(R.id.crtitms)
    NonScrollListView nonScrollListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle(getString(R.string.tit_pay));
        loadcrtitms();
        ButterKnife.inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CartItemAdapter adapter = new CartItemAdapter(PaymentActivity.this, cartProducts);
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
        cartProducts = new ArrayList<>();

        ProductCartInfo c1 = new ProductCartInfo(R.mipmap.ic, "Title 1", "Desc 1", "$ 50");
        cartProducts.add(c1);
        ProductCartInfo c2 = new ProductCartInfo(R.mipmap.ic, "Title 2", "Desc 2", "$ 65");
        cartProducts.add(c2);
        ProductCartInfo c3 = new ProductCartInfo(R.mipmap.ic, "Title 3", "Desc 3", "$ 70");
        cartProducts.add(c3);
        ProductCartInfo c4 = new ProductCartInfo(R.mipmap.ic, "Title 4", "Desc 4", "$ 60");
        cartProducts.add(c4);
        ProductCartInfo c5 = new ProductCartInfo(R.mipmap.ic, "Title 5", "Desc 5", "$ 55");
        cartProducts.add(c5);
        ProductCartInfo c6 = new ProductCartInfo(R.mipmap.ic, "Title 6", "Desc 6", "$ 45");
        cartProducts.add(c6);

    }
}