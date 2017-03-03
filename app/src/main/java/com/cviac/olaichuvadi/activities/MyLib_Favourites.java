package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.FavouritesAdapter;
import com.cviac.olaichuvadi.datamodels.FavouritesInfo;

import java.util.ArrayList;
import java.util.List;

public class MyLib_Favourites extends AppCompatActivity {

    GridView gv;
    FavouritesAdapter adapter;
    List<FavouritesInfo> fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lib__favourites);

        setTitle(R.string.tab_fav);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fav = new ArrayList<>();

        gv = (GridView) findViewById(R.id.favsgrid);
        adapter = new FavouritesAdapter(MyLib_Favourites.this, fav);
        gv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
