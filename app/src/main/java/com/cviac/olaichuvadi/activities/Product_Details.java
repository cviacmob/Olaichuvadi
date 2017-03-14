package com.cviac.olaichuvadi.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.AddToCartResponse;
import com.cviac.olaichuvadi.datamodels.GetCartItemsResponse;
import com.cviac.olaichuvadi.datamodels.Product;
import com.cviac.olaichuvadi.datamodels.ProductDetail;
import com.cviac.olaichuvadi.datamodels.Productdetailresponse;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.cviac.olaichuvadi.utilities.BadgeDrawable;
import com.cviac.olaichuvadi.utilities.OlaichuvadiApp;
import com.cviac.olaichuvadi.utilities.Prefs;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Product_Details extends AppCompatActivity {

    private Button crt, buy;
    private TextView tv, tv1, tv3, tv4, tv5;
    private ImageView iv;
    ProductDetail prdetail = null;
    private LayerDrawable mcartMenuIcon;
    private int mCartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);
        setTitle("Product Details");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Product probj = (Product) i.getSerializableExtra("productobj");
        String tit = probj.getName();

        setTitle(tit);

        tv = (TextView) findViewById(R.id.tit);
        iv = (ImageView) findViewById(R.id.prd_img);
        tv1 = (TextView) findViewById(R.id.prz);
        tv3 = (TextView) findViewById(R.id.pprz);
        tv4 = (TextView) findViewById(R.id.desc);
        tv5 = (TextView) findViewById(R.id.desc_det);
        crt = (Button) findViewById(R.id.cartbtn);
        buy = (Button) findViewById(R.id.buybtn);

        tv3.setPaintFlags(tv3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        crt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prdetail != null) {
                    addToCart(prdetail.getProduct_id(), "1");
                }
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buyitm = new Intent(Product_Details.this, PaymentActivity.class);
                startActivity(buyitm);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.baseurl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        Call<Productdetailresponse> call = api.getProductdetails(probj.getProduct_id());
        call.enqueue(new Callback<Productdetailresponse>() {

            public void onResponse(Response<Productdetailresponse> response, Retrofit retrofit) {
                Productdetailresponse rsp = response.body();
                prdetail = rsp.getProduct().get(0);
                String detail = prdetail.getDescription();
                tv5.setText(Html.fromHtml(Html.fromHtml(detail).toString()));
                tv.setText(prdetail.getName());
                tv1.setText(prdetail.getPrice());
                tv3.setText(prdetail.getSpecial());

                String url = prdetail.getThumb();
                Picasso.with(Product_Details.this).load(url).placeholder(R.mipmap.bookgrd).resize(500, 500).into(iv);
            }

            @Override
            public void onFailure(Throwable t) {
                prdetail = null;
            }
        });
    }

    private void addToCart(String prodId, String quantity) {

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

        final Call<AddToCartResponse> call = api.addToCart(prodId, quantity);
        call.enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Response<AddToCartResponse> response, Retrofit retrofit) {
                AddToCartResponse rsp = response.body();
                getAndSetCartCount();

                OlaichuvadiApp app = (OlaichuvadiApp) getApplication();
                app.notifyCartChange("add");

                Toast.makeText(Product_Details.this, "Book Added to Cart", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(Product_Details.this, "Server Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getAndSetCartCount() {
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

        String token = Prefs.getString("token", null);
        Call<GetCartItemsResponse> call = api.getCartItems();
        call.enqueue(new Callback<GetCartItemsResponse>() {

            public void onResponse(Response<GetCartItemsResponse> response, Retrofit retrofit) {
                GetCartItemsResponse rsp = response.body();
                if (rsp != null) {
                    mCartCount = rsp.getProducts().size();
                    setBadgeCount(Product_Details.this, mcartMenuIcon, mCartCount + "");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_cart_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_cart_badge, badge);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        switch (item.getItemId()) {
            case R.id.cart:
                Intent cart = new Intent(Product_Details.this, MyCartActivity.class);
                startActivity(cart);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        mcartMenuIcon = (LayerDrawable) menu.findItem(R.id.cart).getIcon();
        setBadgeCount(this, mcartMenuIcon, "");
        getAndSetCartCount();
        return true;
    }
}