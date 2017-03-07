package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.activities.Product_Details;
import com.cviac.olaichuvadi.datamodels.AddToCartResponse;
import com.cviac.olaichuvadi.datamodels.ProductCartInfo;
import com.cviac.olaichuvadi.datamodels.ProductDetail;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.utilities.Prefs;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CartItemAdapter extends BaseAdapter {

    private Context mContext;
    private static int counter = 0;
    private String stringVal;
    private List<ProductCartInfo> cartProducts;
    ProductDetail prdetail = null;

    public CartItemAdapter(Context mContext, List<ProductCartInfo> cartProducts) {
        this.mContext = mContext;
        this.cartProducts = cartProducts;
    }

    @Override
    public int getCount() {
        return cartProducts.size();
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
        public ImageView iv;
        public TextView tv1, tv2, tv3, tv4;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View crt = view;
        ViewHolder holder;
        final ProductCartInfo cinfo = cartProducts.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            crt = inflater.inflate(R.layout.cartitems, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) crt.findViewById(R.id.crtimg);
            holder.tv1 = (TextView) crt.findViewById(R.id.crttit);
            holder.tv4 = (TextView) crt.findViewById(R.id.crtprc);
            final TextView tv2 = (TextView) crt.findViewById(R.id.numcounts);
            holder.tv2 = tv2;
            holder.tv3 = (TextView) crt.findViewById(R.id.crtprc);
            ImageButton txtplus = (ImageButton) crt.findViewById(R.id.addprd);
            txtplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("src", "Increasing value...");
                    counter++;
                    stringVal = Integer.toString(counter);
                    tv2.setText(stringVal);
                    addToCart(cinfo.getProduct_id(), "1");
                }
            });
            final ImageButton txtminus = (ImageButton) crt.findViewById(R.id.delprd);
            txtminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("src", "Decreasing value...");
                    counter--;
                    stringVal = Integer.toString(counter);
                    tv2.setText(stringVal);
                    if (counter < 0) {
                        txtminus.setEnabled(false);
                        tv2.setText("0");
                        addToCart(cinfo.getProduct_id(), "-1");
                    }
                }
            });
            crt.setTag(holder);
        } else {
            holder = (ViewHolder) crt.getTag();
        }
        holder.tv4.setText(cinfo.getTotal());
        holder.tv1.setText(cinfo.getName());
        holder.tv2.setText(cinfo.getQuantity());
        return crt;
    }

    private void addToCart(String prodId, String quantity) {

        String token = Prefs.getString("token", null);
        if (token != null) {

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
            okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(mContext.getString(R.string.baseurl))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            OpencartAPIs api = retrofit.create(OpencartAPIs.class);

            final Call<AddToCartResponse> call = api.addToCart(token, prodId, quantity);
            call.enqueue(new Callback<AddToCartResponse>() {
                @Override
                public void onResponse(Response<AddToCartResponse> response, Retrofit retrofit) {
                    AddToCartResponse rsp = response.body();
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}