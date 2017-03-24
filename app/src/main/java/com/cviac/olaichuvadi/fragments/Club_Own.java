package com.cviac.olaichuvadi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.activities.CreateClubActivity;
import com.cviac.olaichuvadi.activities.ReadingClubDetailsActivity;
import com.cviac.olaichuvadi.adapters.Club_OwnAdapter;
import com.cviac.olaichuvadi.datamodels.ClubResponse;
import com.cviac.olaichuvadi.datamodels.ReadingClubInfo;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Club_Own extends Fragment {

    GridView gv;
    List<ReadingClubInfo> club;
    Club_OwnAdapter adapter;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_club__own, container, false);

        club = new ArrayList<>();

        gv = (GridView) view.findViewById(R.id.ownclub);
        adapter = new Club_OwnAdapter(getActivity().getApplicationContext(), club);
        gv.setAdapter(adapter);

        fab = (FloatingActionButton) view.findViewById(R.id.club_own);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_club = new Intent(getActivity().getApplicationContext(), CreateClubActivity.class);
                startActivityForResult(new_club, 909);
            }
        });
        getmyClubs();

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReadingClubInfo rc_rec = club.get(position);
                Intent details = new Intent(getActivity().getApplicationContext(), ReadingClubDetailsActivity.class);
                details.putExtra("Club_Details", rc_rec);
                startActivity(details);
            }
        });
        return view;
    }

    private void getmyClubs() {

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

        final Call<ClubResponse> call = api.getMyClubs();

        call.enqueue(new Callback<ClubResponse>() {
            @Override
            public void onResponse(Response<ClubResponse> response, Retrofit retrofit) {
                ClubResponse rsp = response.body();
                if (rsp != null) {
                    club.clear();
                    club.addAll(rsp.getClubs());
                    adapter.notifyDataSetInvalidated();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                club = null;
            }

        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 909) {
            getmyClubs();
        }
    }
}