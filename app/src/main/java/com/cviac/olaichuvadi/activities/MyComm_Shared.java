package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.SharedAdapter;
import com.cviac.olaichuvadi.datamodels.SharedInfo;

import java.util.ArrayList;
import java.util.List;

public class MyComm_Shared extends AppCompatActivity {

    GridView gv;
    List<SharedInfo> share;
    SharedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comm__shared);
        loadbooks();

        setTitle(R.string.tab_shared);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gv = (GridView) findViewById(R.id.comshrdgrid);
        adapter = new SharedAdapter(this, share);
        gv.setAdapter(adapter);
    }

    private void loadbooks() {

        share = new ArrayList<>();

        SharedInfo s1 = new SharedInfo("Book-1");
        share.add(s1);
        SharedInfo s2 = new SharedInfo("Book-2");
        share.add(s2);
        SharedInfo s3 = new SharedInfo("Book-3");
        share.add(s3);
        SharedInfo s4 = new SharedInfo("Book-4");
        share.add(s4);
        SharedInfo s5 = new SharedInfo("Book-5");
        share.add(s5);
        SharedInfo s6 = new SharedInfo("Book-6");
        share.add(s6);
        SharedInfo s7 = new SharedInfo("Book-7");
        share.add(s7);
        SharedInfo s8 = new SharedInfo("Book-8");
        share.add(s8);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}