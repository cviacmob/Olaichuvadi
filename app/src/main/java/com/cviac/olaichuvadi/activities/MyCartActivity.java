package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.CartItemAdapter;
import com.cviac.olaichuvadi.datamodels.CartTotalInfo;
import com.cviac.olaichuvadi.datamodels.GetCartItemsResponse;
import com.cviac.olaichuvadi.datamodels.ProductCartInfo;
import com.cviac.olaichuvadi.datamodels.ProductDetail;
import com.cviac.olaichuvadi.datamodels.Productdetailresponse;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.utilities.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MyCartActivity extends AppCompatActivity {

    ListView lv;
    List<ProductCartInfo> cartProducts;
    CartItemAdapter adapter;
    List<CartTotalInfo> cartTotals;
    Button proc;
    TextView total, tv, tv1;
    String s;
    static Button notifCount;
    static int mNotifCount = 0;
    private LayerDrawable mCartMenuIcon;
    private int mCartCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        setTitle(R.string.My_Cart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cartProducts = new ArrayList<>();
        loadCartItems();

        adapter = new CartItemAdapter(MyCartActivity.this, cartProducts);
        total = (TextView) findViewById(R.id.netamnt);
        proc = (Button) findViewById(R.id.paybtn);
        lv = (ListView) findViewById(R.id.cartlist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProductCartInfo pinfo = cartProducts.get(i);
                getProduct(pinfo.getProduct_id());
            }
        });
        proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent prd = new Intent(MyCartActivity.this, PaymentActivity.class);
                startActivity(prd);
            }
        });
    }

    private void getProduct(String productId) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        Call<Productdetailresponse> call = api.getProductdetails(productId);
        call.enqueue(new Callback<Productdetailresponse>() {

            public void onResponse(Response<Productdetailresponse> response, Retrofit retrofit) {
                Productdetailresponse rsp = response.body();
                ProductDetail prdet = rsp.getProduct().get(0);
                Intent i = new Intent(MyCartActivity.this, Product_Details.class);
                i.putExtra("productobj", prdet);
                startActivity(i);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadCartItems() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        String token = Prefs.getString("token", null);
        Call<GetCartItemsResponse> call = api.getCartItems(token);
        call.enqueue(new Callback<GetCartItemsResponse>() {

            public void onResponse(Response<GetCartItemsResponse> response, Retrofit retrofit) {
                GetCartItemsResponse rsp = response.body();
                cartProducts.addAll(rsp.getProducts());
                s = String.valueOf(rsp.getProducts().size());
//                tv.setText(s);
                adapter.notifyDataSetInvalidated();
                cartTotals = rsp.getTotals();
                total.setText(cartTotals.get(cartTotals.size() - 1).getText());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        mCartCount = cartProducts.size();
        getMenuInflater().inflate(R.menu.cart, menu);
        mCartMenuIcon = (LayerDrawable) menu.findItem(R.id.cart).getIcon();
        setBadgeCount(this, mCartMenuIcon, String.valueOf(mCartCount++));
        return true;
    }
*/

//    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {
//
//        BadgeDrawable badge;
//
//        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_cart_badge);
//        if (reuse != null && reuse instanceof BadgeDrawable) {
//            badge = (BadgeDrawable) reuse;
//        } else {
//            badge = new BadgeDrawable(context);
//        }
//
//        badge.setCount(count);
//        icon.mutate();
//        icon.setDrawableByLayerId(R.id.ic_cart_badge, badge);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        int id = item.getItemId();
        onBackPressed();
//        if (id == R.id.action_settings) {
//            return true;
//        } else if (id == R.id.cart) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
        return true;
    }
}