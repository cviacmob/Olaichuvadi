package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.AddressAdapter;
import com.cviac.olaichuvadi.datamodels.AddressInfo;
import com.cviac.olaichuvadi.utilities.Prefs;

import java.util.List;

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
//        AddressAdapter adapter = new AddressAdapter(MyAccountActivity.this, addhis);
//        String[] items = {"Apple", "BlackBerry", "Android"};
//        itemList = new ArrayList<String>(Arrays.asList(items));

        lv = (ListView) findViewById(R.id.addlst);

        add_addr_btn = (Button) findViewById(R.id.addbtn);

        tv1.setText(aname);
        tv2.setText(amail);
        tv3.setText(aphone);

        add_addr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addr = new Intent(MyAccountActivity.this, EditaddressActivity.class);
                startActivityForResult(addr, 2);
            }
        });
    }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}