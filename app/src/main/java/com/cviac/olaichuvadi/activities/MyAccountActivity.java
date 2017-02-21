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

import java.util.ArrayList;
import java.util.List;

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
                AddressInfo ad_info = addhis.get(position);
                String add_id = ad_info.getAddress_id();
                int c_id = Prefs.getInt("customer_id", -1);
//                editAddress(add_id, c_id + " ");
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
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

   /* public void editAddress(String address_id, String cust_id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        Call<List<AddressInfo>> call = api.editAddress(address_id, cust_id);
        call.enqueue(new Callback<List<AddressInfo>>() {

            public void onResponse(Response<List<AddressInfo>> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }*/

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