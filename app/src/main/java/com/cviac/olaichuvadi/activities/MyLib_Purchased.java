package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.PurchasedAdapter;
import com.cviac.olaichuvadi.datamodels.BooksInfo;
import com.cviac.olaichuvadi.datamodels.PurchasedInfo;

import java.util.ArrayList;
import java.util.List;

public class MyLib_Purchased extends AppCompatActivity {

    List<PurchasedInfo> lib_pbooks;
    GridView gv;
    PurchasedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lib__purchased);

        getPurchased();

        setTitle(R.string.tab_purch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gv = (GridView) findViewById(R.id.purchgrd);
        gv.setFastScrollEnabled(true);
        adapter = new PurchasedAdapter(MyLib_Purchased.this, lib_pbooks);
        gv.setAdapter(adapter);
    }

    private void getPurchased() {

        lib_pbooks = new ArrayList<>();

        PurchasedInfo p1 = new PurchasedInfo("Book 1");
        lib_pbooks.add(p1);
        PurchasedInfo p2 = new PurchasedInfo("Book 2");
        lib_pbooks.add(p2);
        PurchasedInfo p3 = new PurchasedInfo("Book 3");
        lib_pbooks.add(p3);
        PurchasedInfo p4 = new PurchasedInfo("Book 4");
        lib_pbooks.add(p4);
        PurchasedInfo p5 = new PurchasedInfo("Book 5");
        lib_pbooks.add(p5);
        PurchasedInfo p6 = new PurchasedInfo("Book 6");
        lib_pbooks.add(p6);
        PurchasedInfo p7 = new PurchasedInfo("Book 7");
        lib_pbooks.add(p7);
        PurchasedInfo p8 = new PurchasedInfo("Book 8");
        lib_pbooks.add(p8);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}