package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.CategoryAdapter;
import com.cviac.olaichuvadi.datamodels.CategoriesResponse;
import com.cviac.olaichuvadi.datamodels.Category;
import com.cviac.olaichuvadi.services.OpencartAPIs;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CategoryList_Activity extends AppCompatActivity {

    ListView lv;
    List<Category> categoryList;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        setTitle(getString(R.string.title_categories));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryList = new ArrayList<Category>();

        lv = (ListView) findViewById(R.id.catlist);
        adapter = new CategoryAdapter(this, categoryList);
        lv.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://olaichuvadi.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        final Call<CategoriesResponse> call = api.getCategories();
        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Response<CategoriesResponse> response, Retrofit retrofit) {
                CategoriesResponse rsp = response.body();
                categoryList.addAll(rsp.getCategories());
//                adapter.notifyDataSetChanged();
                adapter.notifyDataSetInvalidated();

            }

            @Override
            public void onFailure(Throwable t) {
                categoryList = null;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Category cta = categoryList.get(pos);
                Intent cat = new Intent();
                cat.putExtra("categoryid", cta.getCategory_id());
                setResult(1000, cat);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}