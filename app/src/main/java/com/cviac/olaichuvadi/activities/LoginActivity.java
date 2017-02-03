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
import com.cviac.olaichuvadi.services.LogininfoResponse;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.utilities.OlaichuvadiApp;
import com.cviac.olaichuvadi.utilities.Prefs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {

    Button log, reg;
    EditText mail, pwd;
    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle(getString(R.string.login));

        mail = (EditText) findViewById(R.id.namebox);
        pwd = (EditText) findViewById(R.id.pwdbox);
        reg = (Button) findViewById(R.id.register);
        log = (Button) findViewById(R.id.login);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lmail = mail.getText().toString();
                String lpwd = pwd.getText().toString();

                if (!isValidEmail(lmail)) {
                    mail.setError("Enter Valid Email-id");
                    return;
                }

                OlaichuvadiApp app = (OlaichuvadiApp) LoginActivity.this.getApplication();
                if (app.isNetworkStatus()) {

                    Prefs.edit();
                    Prefs.putString("isregistered", "true");

                    login(lmail, lpwd);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please Check Your Internet Connection and try again", Toast.LENGTH_LONG).show();
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OlaichuvadiApp app = (OlaichuvadiApp) LoginActivity.this.getApplication();
                if (app.isNetworkStatus()) {
                    Intent reg = new Intent(LoginActivity.this, RegistrationActivity.class);
                    startActivity(reg);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please Check Your Internet Connection and try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void login(String lmail, String lpwd) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpencartAPIs api = retrofit.create(OpencartAPIs.class);
        Call<LogininfoResponse> call = api.login(lmail, lpwd);
        call.enqueue(new Callback<LogininfoResponse>() {
            @Override
            public void onResponse(Response<LogininfoResponse> response, Retrofit retrofit) {
                LogininfoResponse rsp = response.body();
                if (rsp.getCode() == 0) {
                    Intent logn = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(logn);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Login Failed: " + rsp.getCode(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "Login Failed: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
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
