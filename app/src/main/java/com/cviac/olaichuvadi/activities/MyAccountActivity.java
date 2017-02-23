package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.AddressAdapter;
import com.cviac.olaichuvadi.datamodels.AddressInfo;
import com.cviac.olaichuvadi.services.OpencartAPIs;
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

public class MyAccountActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    List<AddressInfo> addhis;
    ListView lv;
    FloatingActionButton fab;
    AddressAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        setTitle(getString(R.string.My_Account));
        loadAddresses();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String aname = Prefs.getString("Regname", "User_Name");
        String amail = Prefs.getString("Regmail", "User_Email");
        String aphone = Prefs.getString("Regphone", "User_Phone");

        tv1 = (TextView) findViewById(R.id.uname);
        tv2 = (TextView) findViewById(R.id.umail);
        tv3 = (TextView) findViewById(R.id.uphone);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        tv1.setText(aname);
        tv2.setText(amail);
        tv3.setText(aphone);

        lv = (ListView) findViewById(R.id.addlst);
        addhis = new ArrayList<>();
        adapter1 = new AddressAdapter(MyAccountActivity.this, addhis);
        lv.setAdapter(adapter1);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addaddr = new Intent(MyAccountActivity.this, EditaddressActivity.class);
                startActivityForResult(addaddr, 140);
            }
        });
    }

    public void loadAddresses() {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        int c_id = Prefs.getInt("customer_id", -1);
        Call<List<AddressInfo>> call = api.getAdresses(c_id + "");
        call.enqueue(new Callback<List<AddressInfo>>() {

            public void onResponse(Response<List<AddressInfo>> response, Retrofit retrofit) {
                List<AddressInfo> rsp = response.body();
                addhis.clear();
                addhis.addAll(rsp);
                adapter1.notifyDataSetInvalidated();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 140) {
            loadAddresses();
        } else if (requestCode == 141) {
            loadAddresses();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}