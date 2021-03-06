package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.activities.MyComm_Authors;
import com.cviac.olaichuvadi.datamodels.AuthorInfo;
import com.cviac.olaichuvadi.datamodels.GeneralResponse;
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

public class AuhtorsAdapter extends BaseAdapter {
    private MyComm_Authors mContext;
    private List<AuthorInfo> authr;

    public AuhtorsAdapter(MyComm_Authors mContext, List<AuthorInfo> authr) {
        this.mContext = mContext;
        this.authr = authr;
    }

    @Override
    public int getCount() {
        return authr.size();
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
        private ImageView iv, iv2;
        private TextView tv1, tv2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View shr = view;
        AuhtorsAdapter.ViewHolder holder;
        final AuthorInfo cinfo = authr.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.author_details, null);
            holder = new AuhtorsAdapter.ViewHolder();
            holder.iv = (ImageView) shr.findViewById(R.id.pub_img);
            holder.iv2 = (ImageView) shr.findViewById(R.id.likpub);
            holder.tv1 = (TextView) shr.findViewById(R.id.pub_tit);
            holder.tv2 = (TextView) shr.findViewById(R.id.likedpub);
            shr.setTag(holder);
        } else {
            holder = (AuhtorsAdapter.ViewHolder) shr.getTag();
        }
        holder.tv1.setText(cinfo.getAuthor_name());
        holder.tv2.setText(cinfo.getLikes());

        String url1 = mContext.getString(R.string.img_club);
        String url2 = cinfo.getAuthor_image();
        StringBuilder url3 = new StringBuilder();
        url3.append(url1 + url2);
        String url = url3.toString();
        if (!url.isEmpty()) {
            Picasso.with(shr.getContext()).load(url).placeholder(R.mipmap.log).resize(500, 500).into(holder.iv);
        }

        holder.iv2.setOnClickListener(new View.OnClickListener() {
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

                Call<GeneralResponse> call = api.likeAuthor(cinfo.getAuthor_id());
                call.enqueue(new Callback<GeneralResponse>() {

                    public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                        GeneralResponse rsp = response.body();
                        if (rsp.getCode() == 0) {
                            mContext.loadLikedAuthors();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        return shr;
    }
}