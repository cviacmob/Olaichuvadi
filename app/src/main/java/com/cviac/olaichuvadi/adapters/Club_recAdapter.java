package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.activities.ReadingClubDetailsActivity;
import com.cviac.olaichuvadi.datamodels.GeneralResponse;
import com.cviac.olaichuvadi.datamodels.ReadingClubInfo;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Club_recAdapter extends BaseAdapter {

    private Context mContext;
    private List<ReadingClubInfo> read;

    public Club_recAdapter(Context mContext, List<ReadingClubInfo> read) {
        this.mContext = mContext;
        this.read = read;
    }

    @Override
    public int getCount() {
        return read.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public static class ViewHolder {
        public TextView tv1, tv2;
        public ImageView iv;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View shr = view;
        Club_recAdapter.ViewHolder holder;
        final ReadingClubInfo cinfo = read.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.club_item, null);
            holder = new ViewHolder();
            holder.tv1 = (TextView) shr.findViewById(R.id.clubtitle);
            holder.tv2 = (TextView) shr.findViewById(R.id.membership);
            holder.iv = (ImageView) shr.findViewById(R.id.club_img);
            shr.setTag(holder);
        } else {
            holder = (Club_recAdapter.ViewHolder) shr.getTag();
        }
        holder.tv1.setText(cinfo.getGroup_name());

        String url1 = mContext.getString(R.string.img_club);
        String url2 = cinfo.getGroup_image();
        StringBuilder url3 = new StringBuilder();
        url3.append(url1 + url2);
        String url = url3.toString();
        if (!url.isEmpty()) {
            Picasso.with(shr.getContext()).load(url).placeholder(R.mipmap.bookgrd).resize(500, 500).into(holder.iv);
        }
        holder.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
                okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);
                okHttpClient.interceptors().add(new AddCookiesInterceptor());
                okHttpClient.interceptors().add(new ReceivedCookiesInterceptor());

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(mContext.getString(R.string.baseurl))
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();

                OpencartAPIs api = retrofit.create(OpencartAPIs.class);

                Call<GeneralResponse> call = api.joinClub(cinfo.getGroup_id());
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
        });
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(mContext, ReadingClubDetailsActivity.class);
                details.putExtra("Club_Details", cinfo);
                mContext.startActivity(details);
            }
        });

        return shr;
    }
}