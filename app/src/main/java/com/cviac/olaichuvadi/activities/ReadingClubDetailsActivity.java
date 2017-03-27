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
import com.cviac.olaichuvadi.adapters.MessagesAdapter;
import com.cviac.olaichuvadi.datamodels.GetPostInfo;
import com.cviac.olaichuvadi.datamodels.GetPostMessagesResponse;
import com.cviac.olaichuvadi.datamodels.ReadingClubInfo;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ReadingClubDetailsActivity extends AppCompatActivity {

    ImageView iv1, iv2, iv3, iv4;
    ListView lv;
    TextView tv1, tv2;
    EditText post;
    MessagesAdapter adapter;
    List<GetPostInfo> posts;
    ReadingClubInfo rc_info;
    String club = getString(R.string.img_club);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_club_details);

        posts = new ArrayList<>();

        Intent i = getIntent();
        rc_info = (ReadingClubInfo) i.getSerializableExtra("Club_Details");

        iv1 = (ImageView) findViewById(R.id.club_pic);
        iv2 = (ImageView) findViewById(R.id.invite_club);
        iv3 = (ImageView) findViewById(R.id.editclub);
        iv4 = (ImageView) findViewById(R.id.deleteclub);
        tv1 = (TextView) findViewById(R.id.rclub_name);
        tv2 = (TextView) findViewById(R.id.rclub_status);
        post = (EditText) findViewById(R.id.writemsg);
        lv = (ListView) findViewById(R.id.msgslist);
        adapter = new MessagesAdapter(ReadingClubDetailsActivity.this, posts);
        lv.setAdapter(adapter);

        if (rc_info.getStatus() == "member") {

        }
        getPostMessages();

        tv1.setText(rc_info.getGroup_name());
        String grpimg = rc_info.getGroup_image();
        StringBuilder url3 = new StringBuilder();
        url3.append(club + grpimg);
        String img = url3.toString();
        if (!img.isEmpty()) {
            Picasso.with(getApplicationContext()).load(img).placeholder(R.mipmap.log).resize(500, 500).into(iv1);
        }
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent invt = new Intent(ReadingClubDetailsActivity.this, InviteMembersActivity.class);
                invt.putExtra("club", rc_info);
                startActivity(invt);
            }
        });
    }

    private void getPostMessages() {

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

        Call<GetPostMessagesResponse> call = api.getPost(rc_info.getGroup_id());
        call.enqueue(new Callback<GetPostMessagesResponse>() {

            public void onResponse(Response<GetPostMessagesResponse> response, Retrofit retrofit) {
                GetPostMessagesResponse rsp = response.body();
                posts.clear();
                posts.addAll(rsp.getPosts());
                adapter.notifyDataSetInvalidated();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}