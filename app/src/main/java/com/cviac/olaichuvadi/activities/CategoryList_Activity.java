package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cviac.olaichuvadi.R;

public class CategoryList_Activity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        setTitle("Categories");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv=(ListView) findViewById(R.id.catlist);
        String[] arr = {"Category-1","Category-2","Category-3","Category-4","Category-5","Category-6"};

        ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(adp);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
