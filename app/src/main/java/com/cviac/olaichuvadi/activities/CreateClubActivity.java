package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.GeneralResponse;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CreateClubActivity extends AppCompatActivity {

    EditText name, desc, loc;
    Button save, cancel;
    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_readclub);

        name = (EditText) findViewById(R.id.edt__clubname);
        desc = (EditText) findViewById(R.id.edt__clubdesc);
        loc = (EditText) findViewById(R.id.edt__clubloc);
        save = (Button) findViewById(R.id.club_ok);
        cancel = (Button) findViewById(R.id.club_cnc);
        rg = (RadioGroup) findViewById(R.id.status);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Rname = name.getText().toString();
                String Rdesc = desc.getText().toString();
                String Rloc = loc.getText().toString();

                int selectedId = rg.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(selectedId);
                String Rstatus = rb.getText().toString();

                if (Rname.isEmpty()) {
                    name.setError("Enter Valid Name");
                } else if (Rdesc.isEmpty()) {
                    desc.setError("Enter Valid Description");
                } else if (Rloc.isEmpty()) {
                    loc.setError("Enter Valid Location");
                } else if (Rstatus.isEmpty()) {
                    rg.requestFocus();
                    Toast.makeText(CreateClubActivity.this, "Choose your Club Status", Toast.LENGTH_LONG).show();
                }

                newClub(Rname, Rdesc, Rstatus, Rloc);
            }
        });
    }

    private void newClub(String rname, String rdesc, String rstatus, String rloc) {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.interceptors().add(new AddCookiesInterceptor());
        okHttpClient.interceptors().add(new ReceivedCookiesInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.baseurl))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        final Call<GeneralResponse> call = api.newClub(rname, rdesc, rstatus, rloc);

        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                GeneralResponse rsp = response.body();
                if (rsp.getCode() == 0) {
                    Intent new_club = new Intent();
                    setResult(909);
                    finish();
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }
}