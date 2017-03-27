package com.cviac.olaichuvadi.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.Club_recAdapter;
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

public class Club_Recommended extends Fragment {

    GridView gv;
    List<ReadingClubInfo> club;
    Club_recAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_club__recommended, container, false);

        club = new ArrayList<>();

        gv = (GridView) view.findViewById(R.id.readingclub_rec);
        adapter = new Club_recAdapter(getActivity().getApplicationContext(), club);
        gv.setAdapter(adapter);

        getRecommendedClubs();
        return view;
    }

    private void getRecommendedClubs() {

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

        final Call<ClubResponse> call = api.getRecommendedClubs();

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
}