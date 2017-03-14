package com.cviac.olaichuvadi.activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.CartItemAdapter;
import com.cviac.olaichuvadi.datamodels.CartTotalInfo;
import com.cviac.olaichuvadi.datamodels.GetCartItemsResponse;
import com.cviac.olaichuvadi.datamodels.ProductCartInfo;
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

public class MyCartActivity extends AppCompatActivity {

    ListView lv;
    List<ProductCartInfo> cartProducts;
    CartItemAdapter adapter;
    List<CartTotalInfo> cartTotals;
    Button proc;
    TextView total;
    int no = 0;
    String s;
    ProgressDialog progressDialog = null;
    private BroadcastReceiver listenCartChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        setTitle(R.string.My_Cart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartProducts = new ArrayList<>();
        loadCartItems();

        total = (TextView) findViewById(R.id.netamnt);
        proc = (Button) findViewById(R.id.paybtn);
        lv = (ListView) findViewById(R.id.cartlist);
        adapter = new CartItemAdapter(MyCartActivity.this, cartProducts);
        lv.setAdapter(adapter);
        proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prd = new Intent(MyCartActivity.this, PaymentActivity.class);
                startActivity(prd);
            }
        });
        listenCartChange = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getStringExtra("action");
                if (action.equalsIgnoreCase("order")) {
                    finish();
                } else {
                    loadCartItems();
                }
            }
        };

        registerReceiver(listenCartChange, new IntentFilter("notifyCartChange"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(listenCartChange);
    }

    public void loadCartItems() {
        progressDialog = new ProgressDialog(MyCartActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

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

        String token = Prefs.getString("token", null);
        Call<GetCartItemsResponse> call = api.getCartItems();
        call.enqueue(new Callback<GetCartItemsResponse>() {

            public void onResponse(Response<GetCartItemsResponse> response, Retrofit retrofit) {
                progressDialog.dismiss();
                GetCartItemsResponse rsp = response.body();
                no = rsp.getProducts().size();
                cartProducts.clear();
                cartProducts.addAll(rsp.getProducts());
                s = String.valueOf(rsp.getProducts().size());
                adapter.notifyDataSetInvalidated();
                cartTotals = rsp.getTotals();
                total.setText(cartTotals.get(cartTotals.size() - 1).getText());
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
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