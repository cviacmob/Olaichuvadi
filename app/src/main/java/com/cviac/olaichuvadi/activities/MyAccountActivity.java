package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.AddressAdapter;
import com.cviac.olaichuvadi.datamodels.AddressInfo;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.utilities.Prefs;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
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
    Button add_addr_btn;
    AddressAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        setTitle(getString(R.string.My_Account));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String aname = Prefs.getString("Regname", "User_Name");
        String amail = Prefs.getString("Regmail", "User_Email");
        String aphone = Prefs.getString("Regphone", "User_Phone");

        tv1 = (TextView) findViewById(R.id.uname);
        tv2 = (TextView) findViewById(R.id.umail);
        tv3 = (TextView) findViewById(R.id.uphone);

        tv1.setText(aname);
        tv2.setText(amail);
        tv3.setText(aphone);

        lv = (ListView) findViewById(R.id.addlst);
        addhis = new ArrayList<>();
        adapter1 = new AddressAdapter(MyAccountActivity.this, addhis);
        lv.setAdapter(adapter1);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int c_id = Prefs.getInt("customer_id", -1);

                AddressInfo ad_info = addhis.get(position);

                String add_id = ad_info.getAddress_id();

                ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> hashfields = new HashMap<String, String>();

                hashfields.put("First_Name", ad_info.getFirstname());
                hashfields.put("Last_Name", ad_info.getLastname());
                hashfields.put("Company", ad_info.getCompany());
                hashfields.put("Address_1", ad_info.getAddress_1());
                hashfields.put("Address_2", ad_info.getAddress_2());
                hashfields.put("City", ad_info.getCity());
                hashfields.put("Pin_Code", ad_info.getPostcode());
                hashfields.put("State", ad_info.getZone());
                hashfields.put("Country", ad_info.getCountry());

                arrayList.add(hashfields);

                editAddress(add_id, c_id + " ", hashfields);
            }
        });

        add_addr_btn = (Button) findViewById(R.id.addbtn);
        /*add_addr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addr = new Intent(MyAccountActivity.this, EditaddressActivity.class);
                startActivityForResult(addr, 2);
            }
        });*/
        loadAddresses();
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
//                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetInvalidated();

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void editAddress(String address_id, String cust_id, HashMap<String, String> hashfields) {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        Call<List<AddressInfo>> call = api.editAddress(address_id, cust_id, hashfields);
        call.enqueue(new Callback<List<AddressInfo>>() {

            public void onResponse(Response<List<AddressInfo>> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 2) {

            String addrs = data.getStringExtra("Address");

//            adapter1 = new AddressAdapter(this, addrs);
//            lv.setAdapter(adapter1);
//
//            adapter1.notifyDataSetChanged();
        }
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}