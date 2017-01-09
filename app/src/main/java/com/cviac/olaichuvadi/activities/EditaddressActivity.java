package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cviac.olaichuvadi.R;

public class EditaddressActivity extends AppCompatActivity {

    Button sub_btn, cncl_btn;
    EditText aname, adr_no, acity, astate, apin, ambl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaddress);
        setTitle("Your Address Here");

        aname = (EditText) findViewById(R.id.aname);
        adr_no = (EditText) findViewById(R.id.adr_no);
        acity = (EditText) findViewById(R.id.acity);
        astate = (EditText) findViewById(R.id.astate);
        apin = (EditText) findViewById(R.id.apin);
        ambl = (EditText) findViewById(R.id.ambl);
        sub_btn = (Button) findViewById(R.id.subbtn);
        cncl_btn = (Button) findViewById(R.id.cnclbtn);

        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = aname.getText().toString();
                String door_st = adr_no.getText().toString();
                String city = acity.getText().toString();
                String state = astate.getText().toString();
                String pin = apin.getText().toString();
                String mbl_no = ambl.getText().toString();

                if (name.length() < 1) {
                    aname.setError("Enter Valid Name");
                    return;
                }
                if (door_st.length() < 1) {
                    adr_no.setError("Enter Valid Door No. / Street");
                    return;
                }
                if (city.length() < 1) {
                    acity.setError("Enter Valid City");
                    return;
                }
                if (state.length() < 1) {
                    astate.setError("Enter Valid State");
                }
                if (pin.length() < 1) {
                    apin.setError("Enter Valid Pincode");
                }
                if (mbl_no.length() < 1) {
                    ambl.setError("Enter Valid Mobile Number");
                }

                Intent sbmt = new Intent(EditaddressActivity.this, MyAccountActivity.class);
                startActivity(sbmt);
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
    }

}