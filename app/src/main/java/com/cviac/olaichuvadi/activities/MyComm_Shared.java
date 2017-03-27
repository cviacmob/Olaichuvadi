package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.SharedAdapter;
import com.cviac.olaichuvadi.datamodels.SharedInfo;

import java.util.List;

public class MyComm_Shared extends AppCompatActivity {

    GridView gv;
    List<SharedInfo> share;
    SharedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comm__shared);

        setTitle(R.string.tab_shared);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gv = (GridView) findViewById(R.id.comshrdgrid);
        adapter = new SharedAdapter(this, share);
        gv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}