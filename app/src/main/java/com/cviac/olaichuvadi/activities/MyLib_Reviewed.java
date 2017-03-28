package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.ReviewedAdapter;
import com.cviac.olaichuvadi.datamodels.PurchasedBooksResponse;
import com.cviac.olaichuvadi.datamodels.ViewReviewInfo;
import com.cviac.olaichuvadi.datamodels.ViewReviewResponse;
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

public class MyLib_Reviewed extends AppCompatActivity {
    GridView gv;
    ReviewedAdapter adapter;
    List<ViewReviewInfo> rev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lib__reviewed);

        setTitle(R.string.tab_rev);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rev = new ArrayList<>();
        gv = (GridView) findViewById(R.id.revgrid);
        adapter = new ReviewedAdapter(MyLib_Reviewed.this, rev);
        gv.setAdapter(adapter);

        reviewedbooks();
    }

    private void reviewedbooks() {

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
        Call<ViewReviewResponse> call = api.viewReview(c_id + "");
        call.enqueue(new Callback<ViewReviewResponse>() {

            public void onResponse(Response<ViewReviewResponse> response, Retrofit retrofit) {
                ViewReviewResponse rsp = response.body();
                rev.clear();
                rev.addAll(rsp.getData());
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