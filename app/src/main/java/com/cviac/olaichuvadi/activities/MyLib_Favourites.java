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

        loadbooks();

        setTitle(R.string.tab_fav);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gv = (GridView) findViewById(R.id.favsgrid);
        adapter = new FavouritesAdapter(MyLib_Favourites.this, fav);
        gv.setAdapter(adapter);
    }

    private void loadbooks() {

        fav = new ArrayList<>();

        FavouritesInfo f1 = new FavouritesInfo("Book-1");
        fav.add(f1);
        FavouritesInfo f2 = new FavouritesInfo("Book-2");
        fav.add(f2);
        FavouritesInfo f3 = new FavouritesInfo("Book-3");
        fav.add(f3);
        FavouritesInfo f4 = new FavouritesInfo("Book-4");
        fav.add(f4);
        FavouritesInfo f5 = new FavouritesInfo("Book-5");
        fav.add(f5);
        FavouritesInfo f6 = new FavouritesInfo("Book-6");
        fav.add(f6);
        FavouritesInfo f7 = new FavouritesInfo("Book-7");
        fav.add(f7);
        FavouritesInfo f8 = new FavouritesInfo("Book-8");
        fav.add(f8);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}