package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.AddressInfo;

import java.lang.reflect.Field;
import java.util.List;

public class EditaddressActivity extends AppCompatActivity {

    List<AddressInfo> addhis;
    Button sub_btn, cncl_btn;
    EditText first_name, last_name, adrs, city, pin, mblno, dist, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaddress);
        setTitle("Your Address Here");

        first_name = (EditText) findViewById(R.id.afname);
        last_name = (EditText) findViewById(R.id.alname);
        adrs = (EditText) findViewById(R.id.aaddr);
        city = (EditText) findViewById(R.id.acity);
        pin = (EditText) findViewById(R.id.apin);
        mblno = (EditText) findViewById(R.id.mob_no);
        sub_btn = (Button) findViewById(R.id.subbtn);
        cncl_btn = (Button) findViewById(R.id.cnclbtn);
        dist = (EditText) findViewById(R.id.adist);
        state = (EditText) findViewById(R.id.astate);

        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname = first_name.getText().toString();
                String lname = last_name.getText().toString();
                String address = adrs.getText().toString();
                String aacty = city.getText().toString();
                String pin_no = pin.getText().toString();
                String mobile = mblno.getText().toString();
                String pay_city = dist.getText().toString();
                String pay_state = state.getText().toString();

                if (fname.length() < 1) {
                    first_name.setError("Enter First Name");
                    return;
                }
                if (lname.length() < 1) {
                    last_name.setError("Enter Last Name");
                }
                if (address.length() < 1) {
                    adrs.setError("Enter Valid Door No. / Street");
                    return;
                }
                if (aacty.length() < 1) {
                    city.setError("Enter Valid City");
                    return;
                }
                if (pin_no.length() < 1) {
                    pin.setError("Enter Valid Pincode");
                }
                if (pay_city.length() < 1) {
                    dist.setError("Enter District");
                }
                if (pay_state.length() < 1) {
                    state.setError("Enter State");
                }
                if (mobile.length() < 1) {
                    mblno.setError("Enter Valid Mobile Number");
                }

                String adrs_body = getAddress(fname, lname, address, aacty, pin_no, mobile, pay_state, pay_city);

                Intent sbmt = new Intent();
                sbmt.putExtra("Address", adrs_body);
                setResult(2, sbmt);
                finish();
            }
        });
        cncl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cncl = new Intent(EditaddressActivity.this, MyAccountActivity.class);
                startActivity(cncl);
                finish();
            }
        });

//        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                adapterView.getItemAtPosition(i);
//
//                String dist_pos = "dist_" + i;
//                int resID = getId(dist_pos, R.array.class);
//                final String[] dists = view.getContext().getResources().getStringArray(resID);
//                ArrayAdapter<CharSequence> adapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, dists);
//                scity.setAdapter(adapter);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }

    private String getAddress(String fname, String lname, String address, String aacty, String pin_no, String mobile, String pay_state, String pay_city) {
        StringBuilder msgBody = new StringBuilder();

        msgBody.append(fname + " " + lname + "\n");
        msgBody.append(address + ", " + aacty + "," + "\n");
        msgBody.append(pay_city + ", " + pay_state + "." + "\n");
        msgBody.append(pin_no + "\n");
        msgBody.append(mobile + "\n");

        return msgBody.toString();
    }

    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }
}