package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.PurchasedAdapter;
import com.cviac.olaichuvadi.datamodels.PurchasedBooksInfo;
import com.cviac.olaichuvadi.datamodels.PurchasedBooksResponse;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.cviac.olaichuvadi.utilities.Prefs;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MyLib_Purchased extends AppCompatActivity {

    List<PurchasedBooksInfo> lib_pbooks;
    GridView gv;
    PurchasedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lib__purchased);

        setTitle(R.string.tab_purch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lib_pbooks = new ArrayList<>();

        gv = (GridView) findViewById(R.id.purchgrd);
        adapter = new PurchasedAdapter(MyLib_Purchased.this, lib_pbooks);
        gv.setAdapter(adapter);

        getPurchasedBooks();
    }

    private void getPurchasedBooks() {

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

        int c_id = Prefs.getInt("customer_id", -1);
        Call<PurchasedBooksResponse> call = api.viewPurchasedbooks(c_id + "");
        call.enqueue(new Callback<PurchasedBooksResponse>() {

            public void onResponse(Response<PurchasedBooksResponse> response, Retrofit retrofit) {
                PurchasedBooksResponse rsp = response.body();
                lib_pbooks.clear();
                lib_pbooks.addAll(rsp.getPosts());
                adapter.notifyDataSetInvalidated();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}