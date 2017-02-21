package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.AddressInfo;

import java.util.List;

public class EditaddressActivity extends AppCompatActivity {

    List<AddressInfo> addhis;
    Button sub_btn, cncl_btn;
    EditText first_name, last_name, cmpny, addrs1, addrs2, city, pin_code, state, country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaddress);
        setTitle("Your Address Here");

        first_name = (EditText) findViewById(R.id.fname_ed);
        last_name = (EditText) findViewById(R.id.lname_ed);
        cmpny = (EditText) findViewById(R.id.cmpny_ed);
        addrs1 = (EditText) findViewById(R.id.addr1_ed);
        addrs2 = (EditText) findViewById(R.id.addr2_ed);
        city = (EditText) findViewById(R.id.city_ed);
        pin_code = (EditText) findViewById(R.id.pin_ed);
        state = (EditText) findViewById(R.id.state_ed);
        country = (EditText) findViewById(R.id.cntry_ed);

        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = first_name.getText().toString();
                String lname = last_name.getText().toString();
                String company = cmpny.getText().toString();
                String addres1 = addrs1.getText().toString();
                String addres2 = addrs2.getText().toString();
                String cty = city.getText().toString();
                String pin = pin_code.getText().toString();
                String stt = state.getText().toString();
                String cntry = country.getText().toString();

                if (fname.length() < 1) {
                    first_name.setError("Enter Valid First Name");
                    return;
                } else if (lname.length() < 1) {
                    last_name.setError("Enter Valid Last Name");
                    return;
                } else if (company.length() < 1) {
                    cmpny.setError("Enter to Proceed");
                    return;
                } else if (addres1.length() < 1) {
                    addrs1.setError("Enter Valid Address");
                    return;
                } else if (addres2.length() < 1) {
                    addrs2.setError("Enter Valid Address");
                    return;
                } else if (cty.length() < 1) {
                    city.setError("Enter Valid City");
                    return;
                } else if (pin.length() < 1) {
                    pin_code.setError("Enter Valid PIN Code");
                    return;
                } else if (stt.length() < 1) {
                    state.setError("Enter Valid State");
                    return;
                } else if (cntry.length() < 1) {
                    country.setError("Enter Valid Country");
                    return;
                }


            }
        });

/*        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String adrs_body = getAddress(fname, lname, address, aacty, pin_no, mobile, pay_state, pay_city);

                *//*String ins_addrs = passAddr(fname, lname, address, aacty, pin_no, mobile, pay_state, pay_city);*//*

                Intent sbmt = new Intent();
                sbmt.putExtra("Address", adrs_body);
                setResult(2, sbmt);
                finish();
            }
        });*/
        cncl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cncl = new Intent(EditaddressActivity.this, MyAccountActivity.class);
                startActivity(cncl);
                finish();
            }
        });
    }

/*    private String passAddr(String fname, String lname, String address, String aacty, String pin_no, String mobile, String pay_state, String pay_city) {

        AddressInfo addr = new AddressInfo(fname, lname, address, aacty, pay_city, pay_state, pin_no, mobile);

        addr.setFname(fname);
        addr.setLname(lname);
        addr.setAddr(address);
        addr.setCity(aacty);
        addr.setPin_code(pin_no);
        addr.setMobileno(mobile);
        addr.setState(pay_state);
        addr.setDist(pay_city);

        return addr.toString();
    }*/

/*    private String getAddress(String fname, String lname, String address, String aacty, String pin_no, String mobile, String pay_state, String pay_city) {
        StringBuilder msgBody = new StringBuilder();

        msgBody.append(fname + " " + lname + "\n");
        msgBody.append(address + ", " + aacty + "," + "\n");
        msgBody.append(pay_city + ", " + pay_state + "." + "\n");
        msgBody.append(pin_no + "\n");
        msgBody.append(mobile + "\n");

        return msgBody.toString();
    }*/
}