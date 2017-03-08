package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.ProductsAdapter;
import com.cviac.olaichuvadi.datamodels.CategoryProductsResponse;
import com.cviac.olaichuvadi.datamodels.Product;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchResultActivity extends AppCompatActivity {

    GridView gv;
    List<Product> rowListItem;
    ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String catid = i.getStringExtra("categoryid");
        String catname = i.getStringExtra("categoryname");

        setTitle(catname);

        gv = (GridView) findViewById(R.id.prdts1);
        gv.setFastScrollEnabled(true);
        rowListItem = new ArrayList<>();
        adapter = new ProductsAdapter(SearchResultActivity.this, rowListItem);
        gv.setAdapter(adapter);

        getProducts(catid);
    }

    public void getProducts(String catId) {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.interceptors().add(new AddCookiesInterceptor());
        okHttpClient.interceptors().add(new ReceivedCookiesInterceptor());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.baseurl))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
       // String credentials = Credentials.basic("olaichuvadi", "cviac");
        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

       /* Map<String, String> map = new HashMap<>();
        map.put("Authorization",credentials);*/
        final Call<CategoryProductsResponse> call = api.getProducts(catId);

        call.enqueue(new Callback<CategoryProductsResponse>() {
            @Override
            public void onResponse(Response<CategoryProductsResponse> response, Retrofit retrofit) {
                CategoryProductsResponse rsp = response.body();
                rowListItem.clear();
                rowListItem.addAll(rsp.getProducts());
//                adapter.notifyDataSetChanged();
                adapter.notifyDataSetInvalidated();
            }

            @Override
            public void onFailure(Throwable t) {
                rowListItem = null;
            }

        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Product pr = rowListItem.get(pos);
                Intent prd = new Intent(SearchResultActivity.this, Product_Details.class);
                prd.putExtra("productobj", pr);
                startActivity(prd);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
