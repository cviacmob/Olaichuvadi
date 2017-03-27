package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.BooksAdapter;
import com.cviac.olaichuvadi.datamodels.ViewAddedBooksInfo;
import com.cviac.olaichuvadi.datamodels.ViewAddedBooksResponse;
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

public class MyLib_Books extends AppCompatActivity {

    GridView gv;
    List<ViewAddedBooksInfo> lib_books;
    BooksAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lib__books);

        setTitle(R.string.tab_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lib_books = new ArrayList<>();
        gv = (GridView) findViewById(R.id.booksgrd);
        adapter = new BooksAdapter(MyLib_Books.this, lib_books);
        gv.setAdapter(adapter);
        getMyBooks();
        fab = (FloatingActionButton) findViewById(R.id.fab_books);
    }

    private void getMyBooks() {

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
        Call<ViewAddedBooksResponse> call = api.viewAddedBooks(c_id + "");
        call.enqueue(new Callback<ViewAddedBooksResponse>() {

            public void onResponse(Response<ViewAddedBooksResponse> response, Retrofit retrofit) {
                ViewAddedBooksResponse rsp = response.body();
                lib_books.clear();
                lib_books.addAll(rsp.getData());
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