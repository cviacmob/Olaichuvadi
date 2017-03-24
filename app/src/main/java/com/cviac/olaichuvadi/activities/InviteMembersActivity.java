package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.GeneralResponse;
import com.cviac.olaichuvadi.datamodels.ReadingClubInfo;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class InviteMembersActivity extends AppCompatActivity {

    ReadingClubInfo rc_info;
    ImageView iv1, iv2;
    TextView tv;
    ListView lv;
    EditText enter_email;
    String mail_id;
    String club = getString(R.string.img_club);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_members);

        Intent i = getIntent();
        rc_info = (ReadingClubInfo) i.getSerializableExtra("club");

        iv1 = (ImageView) findViewById(R.id.invtimg);
        iv2 = (ImageView) findViewById(R.id.clk);
        tv = (TextView) findViewById(R.id.invtclub_name);
        enter_email = (EditText) findViewById(R.id.edtmmbrs);
        lv = (ListView) findViewById(R.id.invtdlst);

        tv.setText(rc_info.getGroup_name());
        String clubimg = rc_info.getGroup_image();
        StringBuilder url3 = new StringBuilder();
        url3.append(club + clubimg);
        String img = url3.toString();
        if (!img.isEmpty()) {
            Picasso.with(getApplicationContext()).load(img).placeholder(R.mipmap.log).resize(500, 500).into(iv1);
        }
        mail_id = enter_email.getText().toString();
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInvite(rc_info.getGroup_id(), mail_id);
            }
        });
    }

    private void sendInvite(String group_id, String mail_id) {

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

        Call<GeneralResponse> call = api.inviteMembers(group_id, mail_id);
        call.enqueue(new Callback<GeneralResponse>() {

            public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                GeneralResponse rsp = response.body();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}