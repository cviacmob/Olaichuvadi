package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.AddressInfo;
import com.cviac.olaichuvadi.utilities.Prefs;

import java.util.ArrayList;
import java.util.List;

public class MyAccountActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    List<AddressInfo> addhis;
    ListView lv;
    Button btn;
    ArrayList<String> itemList;
    ArrayAdapter<String> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        setTitle(getString(R.string.My_Account));
//        loadaddrs();

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

        btn = (Button) findViewById(R.id.addbtn);

        tv1.setText(aname);
        tv2.setText(amail);
        tv3.setText(aphone);

        btn.setOnClickListener(new View.OnClickListener() {
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

            itemList.add(addrs);
            adapter1 = new ArrayAdapter<String>(this, R.layout.list_item, R.id.listitem, itemList);
            lv.setAdapter(adapter1);

            adapter1.notifyDataSetChanged();
        }
    }

    //            private void loadaddrs() {
//
//        addhis = new ArrayList<>();
//        AddressInfo a1 = new AddressInfo("Gunaseelan", "10-10-23A, Kaliamman Kovil Street", "Batlagundu", "Dindigul", "Tamilnadu", "624202", "8489674524");
//        addhis.add(a1);
//        AddressInfo a2 = new AddressInfo("Arunkumar", "59/23 Sakthi Avenue", "Kotturpuram", "Chennai", "Tamilnadu", "600085", "9751946236");
//        addhis.add(a2);
//        AddressInfo a3 = new AddressInfo("Santhi", "VSI Estate", "Thiruvanmiyur", "Chennai", "Tamilnadu", "600041", "9786512550");
//        addhis.add(a3);
//        AddressInfo a4 = new AddressInfo("Indu", "Syed Ammal School opp.", "Rameshwaram", "Ramnathapuram", "Tamilnadu", "642259", "9500610994");
//        addhis.add(a4);
//        AddressInfo a5 = new AddressInfo("Ganesh", "No.1, Gandhi Mandapam", "Guindy", "Chennai", "Tamilnadu", "600025", "9187545980");
//        addhis.add(a5);
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}