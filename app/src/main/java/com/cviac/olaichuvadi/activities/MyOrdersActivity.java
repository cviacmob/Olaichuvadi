package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.OrderAdapter;
import com.cviac.olaichuvadi.datamodels.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {

    ListView lv;
    List<OrderInfo> ordhis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        setTitle(R.string.My_Orders);
        loadorders();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        OrderAdapter adapter = new OrderAdapter(MyOrdersActivity.this, ordhis);
        lv = (ListView) findViewById(R.id.ordrlist);
        lv.setAdapter(adapter);
    }

    private void loadorders() {
        ordhis = new ArrayList<>();

        OrderInfo p1 = new OrderInfo(R.mipmap.ic, "Book-1", "Delivered", "On 26-12-2016");
        ordhis.add(p1);
        OrderInfo p2 = new OrderInfo(R.mipmap.ic, "Book-2", "Will be Delivered", "On 03-01-2017");
        ordhis.add(p2);
        OrderInfo p3 = new OrderInfo(R.mipmap.ic, "Book-3", "Delivered", "On 29-12-2016");
        ordhis.add(p3);
        OrderInfo p4 = new OrderInfo(R.mipmap.ic, "Book-4", "Will be Delivered", "On 05-01-2017");
        ordhis.add(p4);
        OrderInfo p5 = new OrderInfo(R.mipmap.ic, "Book-5", "Will be Delivered", "On 03-01-2017");
        ordhis.add(p5);
        OrderInfo p6 = new OrderInfo(R.mipmap.ic, "Book-6", "Delivered", "On 01-01-2017");
        ordhis.add(p6);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}