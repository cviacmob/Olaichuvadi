package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.utilities.Prefs;

public class MyAccountActivity extends AppCompatActivity {

    Button lgot;
    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        setTitle("My Account");

        String aname = Prefs.getString("Name", "");
        String amail = Prefs.getString("Mail", "");
        String aphone = Prefs.getString("Phone", "");

        tv1 = (TextView) findViewById(R.id.uname);
        tv2 = (TextView) findViewById(R.id.umail);
        tv3 = (TextView) findViewById(R.id.uphone);
        lgot = (Button) findViewById(R.id.exit);

        tv1.setText(aname);
        tv2.setText(amail);
        tv3.setText(aphone);

        lgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
                Intent lgot = new Intent(MyAccountActivity.this, LoginActivity.class);
                startActivity(lgot);
            }
        });
    }

    private void logout() {
        Prefs.edit();
        Prefs.remove("isregistered");
    }
}
