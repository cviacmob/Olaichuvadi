package com.cviac.olaichuvadi.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.activities.MyCartActivity;
import com.cviac.olaichuvadi.activities.PaymentActivity;
import com.cviac.olaichuvadi.datamodels.AddToCartResponse;
import com.cviac.olaichuvadi.datamodels.ProductCartInfo;
import com.cviac.olaichuvadi.datamodels.ProductDetail;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.cviac.olaichuvadi.utilities.OlaichuvadiApp;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CartItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<ProductCartInfo> cartProducts;
    ProgressDialog progressDialog = null;

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
        public ImageButton remove, txtplus, txtminus;
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
            holder.txtplus = (ImageButton) crt.findViewById(R.id.addprd);
            holder.txtplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addToCart(cinfo, 1);
                }
            });
            holder.txtminus = (ImageButton) crt.findViewById(R.id.delprd);
            holder.txtminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cinfo.getQuantity().equals("1")) {
                        return;
                    }
                    addToCart(cinfo, -1);
                }
            });
            holder.remove = (ImageButton) crt.findViewById(R.id.delbtn);
            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFromCart(cinfo);
                }
            });
            crt.setTag(holder);
        } else {
            holder = (ViewHolder) crt.getTag();
        }
        String prd_img = cinfo.getImage();
        Picasso.with(mContext).load(prd_img).placeholder(R.mipmap.bookgrd).resize(60, 60).into(holder.iv);
        holder.tv4.setText(cinfo.getTotal());
        holder.tv1.setText(cinfo.getName());
        holder.tv2.setText(cinfo.getQuantity());
        return crt;
    }

    private void addToCart(final ProductCartInfo cinfo, final int quantity) {
        progressDialog = new ProgressDialog(mContext, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Adding...");
        progressDialog.setCancelable(false);
        progressDialog.show();

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

        String prod_id = cinfo.getProduct_id();

        final Call<AddToCartResponse> call = api.addToCart(prod_id, quantity + "");
        call.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Response<AddToCartResponse> response, Retrofit retrofit) {
                progressDialog.dismiss();
                AddToCartResponse rsp = response.body();
                MyCartActivity actv = (MyCartActivity) mContext;
                actv.loadCartItems();
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    private void removeFromCart(final ProductCartInfo cinfo) {
        progressDialog = new ProgressDialog(mContext, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Removing...");
        progressDialog.setCancelable(false);
        progressDialog.show();

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

        String prod_id = cinfo.getProduct_id();

        final Call<AddToCartResponse> call = api.removeFromCart(cinfo.getCart_id());
        call.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Response<AddToCartResponse> response, Retrofit retrofit) {
                progressDialog.dismiss();
                AddToCartResponse rsp = response.body();

                MyCartActivity actv1 = (MyCartActivity) mContext;
                actv1.loadCartItems();

                OlaichuvadiApp app = (OlaichuvadiApp) ((MyCartActivity) mContext).getApplication();
                app.notifyCartChange("remove");
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
            }
        });
    }
}