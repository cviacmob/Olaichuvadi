package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.activities.MyComm_Publishers;
import com.cviac.olaichuvadi.datamodels.GeneralResponse;
import com.cviac.olaichuvadi.datamodels.PublisherInfo;
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

public class PublishersAdapter extends BaseAdapter {
    private MyComm_Publishers mContext;
    private List<PublisherInfo> publ;

    public PublishersAdapter(MyComm_Publishers mContext, List<PublisherInfo> publ) {
        this.mContext = mContext;
        this.publ = publ;
    }

    @Override
    public int getCount() {
        return publ.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        private ImageView iv, iv2;
        private TextView tv1, tv2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View pub = convertView;
        ViewHolder holder;
        final PublisherInfo pinfo = publ.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            pub = inflater.inflate(R.layout.activity_publishers_adapter, null);
            holder = new PublishersAdapter.ViewHolder();
            holder.iv = (ImageView) pub.findViewById(R.id.pub_img);
            holder.iv2 = (ImageView) pub.findViewById(R.id.likpub);
            holder.tv1 = (TextView) pub.findViewById(R.id.pub_tit);
            holder.tv2 = (TextView) pub.findViewById(R.id.likedpub);
            pub.setTag(holder);
        } else {
            holder = (PublishersAdapter.ViewHolder) pub.getTag();
        }
        holder.tv1.setText(pinfo.getPublisher_name());
        holder.tv2.setText(pinfo.getLikes());

        String url1 = mContext.getString(R.string.img_club);
        String url2 = pinfo.getPublisher_image();
        StringBuilder url3 = new StringBuilder();
        url3.append(url1 + url2);
        String url = url3.toString();
        if (!url.isEmpty()) {
            Picasso.with(pub.getContext()).load(url).placeholder(R.mipmap.log).resize(500, 500).into(holder.iv);
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

                Call<GeneralResponse> call = api.likePublisher(pinfo.getPublisher_id());
                call.enqueue(new Callback<GeneralResponse>() {

                    public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                        GeneralResponse rsp = response.body();
                        if (rsp.getCode() == 0) {
                            mContext.loadLikedPublishers();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
        return pub;
    }
}