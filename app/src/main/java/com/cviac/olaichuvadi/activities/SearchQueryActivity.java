package com.cviac.olaichuvadi.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.ProductsAdapter;
import com.cviac.olaichuvadi.datamodels.CategoryProductsResponse;
import com.cviac.olaichuvadi.datamodels.Product;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchQueryActivity extends AppCompatActivity {

    TextView tv;
    GridView gv;
    List<Product> rowListItem;
    ProductsAdapter adapter;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_query);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        if (Intent.ACTION_SEARCH.equals(i.getAction())) {
            query = i.getStringExtra(SearchManager.QUERY);
        }

        setTitle(query);

        gv = (GridView) findViewById(R.id.search_grd);
        tv = (TextView) findViewById(R.id.empty_search);
        gv.setFastScrollEnabled(true);
        rowListItem = new ArrayList<>();
        adapter = new ProductsAdapter(SearchQueryActivity.this, rowListItem);
        gv.setAdapter(adapter);

        searchProducts(query);
    }

    private void searchProducts(String query) {

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

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        final Call<CategoryProductsResponse> call = api.search(query);

        call.enqueue(new Callback<CategoryProductsResponse>() {
            @Override
            public void onResponse(Response<CategoryProductsResponse> response, Retrofit retrofit) {
                CategoryProductsResponse rsp = response.body();

                if (rsp.getProducts().size() != 0) {
                    rowListItem.clear();
                    rowListItem.addAll(rsp.getProducts());
                    adapter.notifyDataSetInvalidated();
                } else if (rsp.getProducts().size() == 0) {
                    tv.setText("No Prodcuts matches the Search Criteria");
                }
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
                Intent prd = new Intent(SearchQueryActivity.this, Product_Details.class);
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