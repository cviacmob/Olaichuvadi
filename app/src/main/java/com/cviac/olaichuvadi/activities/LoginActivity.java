package com.cviac.olaichuvadi.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.LogininfoResponse;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.cviac.olaichuvadi.utilities.OlaichuvadiApp;
import com.cviac.olaichuvadi.utilities.Prefs;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {

    Button log;
    EditText mail, pwd;
    TextView clk;
    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle(getString(R.string.login));

        mail = (EditText) findViewById(R.id.namebox);
        pwd = (EditText) findViewById(R.id.pwdbox);
        mail.setText("rameshoh1@gmail.com");
        pwd.setText("123456");
        log = (Button) findViewById(R.id.login);
        clk = (TextView) findViewById(R.id.textView3);

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

                    login(lmail, lpwd);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please Check Your Internet Connection and try again", Toast.LENGTH_LONG).show();
                }
            }
        });

        clk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.interceptors().add(new AddCookiesInterceptor());
        okHttpClient.interceptors().add(new ReceivedCookiesInterceptor());

        Prefs.remove("oc_cookies");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.baseurl))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        OpencartAPIs api = retrofit.create(OpencartAPIs.class);
        Call<LogininfoResponse> call = api.login(lmail, lpwd);
        call.enqueue(new Callback<LogininfoResponse>() {
            @Override
            public void onResponse(Response<LogininfoResponse> response, Retrofit retrofit) {
                LogininfoResponse rsp = response.body();

                if (rsp.getCode() == 0) {

                    Prefs.edit();
                    Prefs.putString("isregistered", "true");
                    Prefs.putInt("customer_id", rsp.getCustomer_id());
                    Prefs.putString("Regname", rsp.getFirstname());
                    Prefs.putString("Regmail", rsp.getEmail());
                    Prefs.putString("Regphone", rsp.getTelephone());
                    Prefs.putInt("customer_id", rsp.getCustomer_id());

                    progressDialog.dismiss();

                    Intent logn = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(logn);
                    finish();
                } else {

                    progressDialog.dismiss();

                    Toast.makeText(LoginActivity.this,
                            "Login Failed: " + rsp.getCode(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

                progressDialog.dismiss();

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