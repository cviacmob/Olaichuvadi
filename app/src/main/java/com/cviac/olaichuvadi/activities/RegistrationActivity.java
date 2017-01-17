package com.cviac.olaichuvadi.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReginfoResponse;
import com.cviac.olaichuvadi.utilities.OlaichuvadiApp;
import com.cviac.olaichuvadi.utilities.Prefs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, mail, phone, pwd, cnfpwd;
    Button regist;
    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = (EditText) findViewById(R.id.namebox);
        mail = (EditText) findViewById(R.id.mailbox);
        phone = (EditText) findViewById(R.id.phonebox);
        pwd = (EditText) findViewById(R.id.pwdbox);
        cnfpwd = (EditText) findViewById(R.id.cnfpwdbox);
        regist = (Button) findViewById(R.id.regist);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dname = name.getText().toString();
                String dmail = mail.getText().toString();
                String dphone = phone.getText().toString();
                String dpwd = pwd.getText().toString();
                String dcnfpwd = cnfpwd.getText().toString();

                if (name.length() < 1) {
                    name.setError("Enter Valid Name");
                    return;
                }
                if (phone.length() < 9) {
                    phone.setError("Enter Valid Mobile Number");
                    return;
                }
                if (!isValidEmail(dmail)) {
                    mail.setError("Enter Valid Email-id");
                    return;
                }
                if (!dpwd.equals(dcnfpwd)) {
                    pwd.setError("Password doesn't Matches");
                    cnfpwd.requestFocus();
                    return;
                }

                OlaichuvadiApp app = (OlaichuvadiApp) RegistrationActivity.this.getApplication();
                if (app.isNetworkStatus()) {

                    Prefs.edit();
                    Prefs.putString("Name", dname);
                    Prefs.putString("Mail", dmail);
                    Prefs.putString("Phone", dphone);

                    register(dname, dname, dmail, dphone, dpwd, dcnfpwd);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please Check Your Internet Connection and try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void register(String firstname, String lastname, String email1, String mob, String pswd, String cpswd) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpencartAPIs api = retrofit.create(OpencartAPIs.class);
        Call<ReginfoResponse> call = api.register(firstname, lastname, email1, mob, pswd, cpswd);
        call.enqueue(new Callback<ReginfoResponse>() {
            @Override
            public void onResponse(Response<ReginfoResponse> response, Retrofit retrofit) {
                ReginfoResponse rsp = response.body();
                if (rsp.getCode() == 0) {
                    //Prefs.putString("Customer_ID",rsp.getCustomer().getCustomer_id());
                    Intent logn = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(logn);
                    finish();
                } else {
                    Toast.makeText(RegistrationActivity.this,
                            "Registration Failed: " + rsp.getCode(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RegistrationActivity.this,
                        "Registration Failed: "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    protected boolean isValidEmail(String email) {

        String EMAILPATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAILPATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}