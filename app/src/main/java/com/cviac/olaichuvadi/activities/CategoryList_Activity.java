package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

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

    ExpandableListView lv;
    List<Category> categoryList;
    CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        setTitle(getString(R.string.Categories));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryList = new ArrayList<Category>();

        lv = (ExpandableListView) findViewById(R.id.catlist);
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

        lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                List<Category> childs = categoryList.get(groupPosition).getCategories();

                if (childs != null && childs.size() > 0) {
                    //No Action
                } else {
                    Category ct = categoryList.get(groupPosition);
                    Intent cat = new Intent(CategoryList_Activity.this, SearchResultActivity.class);
                    cat.putExtra("categoryid", ct.getCategory_id());
                    startActivity(cat);
                }
                return false;
            }
        });

        lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                List<Category> childs = categoryList.get(groupPosition).getCategories();
                if (childs != null) {
                    Category ct = childs.get(childPosition);
                    Intent cat = new Intent(CategoryList_Activity.this, SearchResultActivity.class);
                    cat.putExtra("categoryid", ct.getCategory_id());
                    startActivity(cat);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}