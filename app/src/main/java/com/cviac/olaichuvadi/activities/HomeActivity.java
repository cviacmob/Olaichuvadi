package com.cviac.olaichuvadi.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.ProductsAdapter;
import com.cviac.olaichuvadi.datamodels.CategoryProductsResponse;
import com.cviac.olaichuvadi.datamodels.GetCartItemsResponse;
import com.cviac.olaichuvadi.datamodels.LoginResponse;
import com.cviac.olaichuvadi.datamodels.Product;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.utilities.BadgeDrawable;
import com.cviac.olaichuvadi.utilities.Prefs;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    GridView gv;
    List<Product> rowListItem;
    ProductsAdapter adapter;

    private LayerDrawable mcartMenuIcon;
    private int mCartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rowListItem = new ArrayList<Product>();
        gv = (GridView) findViewById(R.id.prdts);
        adapter = new ProductsAdapter(HomeActivity.this, rowListItem);
        gv.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setUserdetails(navigationView);

        getSetToken();

        refresh("61");
    }

    private void getSetToken() {
        String token = Prefs.getString("token", null);
        if (token == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://nheart.cviac.com/index.php?route=api/login")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OpencartAPIs api = retrofit.create(OpencartAPIs.class);

            String apiKey = "um5xn7zaF0RfeAzhN5vG3xsqeeFjupkgOjvtqSubhcR68yw1yg5l1nu4z0EIaYx2HLqRwlvkLGCnFL8EIG0T61L3AtD1v5HNCTPYKdksMXZrCGWGdFX1p5z8KKGQz7lBQzczWxopiQcsUXKr6B7vNasiWEpZ5pNWTjhZgMMOUILMKmnj335u67xLaO334LRmgDiEA6IDyR4Hmilqp3xjce2SvPJeRDwPuINSmSFLFxJO8qUSiF6xObvNhqZZAkey";
            final Call<LoginResponse> call = api.login(apiKey);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
                    int code = response.code();
                    LoginResponse rsp = response.body();
                    Prefs.putString("token", rsp.getToken());
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public void refresh(String catId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        final Call<CategoryProductsResponse> call = api.getProducts(catId);
        call.enqueue(new Callback<CategoryProductsResponse>() {
            @Override
            public void onResponse(Response<CategoryProductsResponse> response, Retrofit retrofit) {
                CategoryProductsResponse rsp = response.body();
                rowListItem.clear();
                rowListItem.addAll(rsp.getProducts());
                //adapter.notifyDataSetChanged();
                adapter.notifyDataSetInvalidated();
            }

            @Override
            public void onFailure(Throwable t) {
                rowListItem = null;
            }

        });

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Product pr = rowListItem.get(pos);
                Intent prd = new Intent(HomeActivity.this, Product_Details.class);
                prd.putExtra("productobj", pr);
                startActivity(prd);
            }
        });

    }

    private void setUserdetails(NavigationView navigationView) {
        View hview = navigationView.getHeaderView(0);
        TextView un = (TextView) hview.findViewById(R.id.nav_name);
        TextView um = (TextView) hview.findViewById(R.id.nav_mail);

        String name = Prefs.getString("Name", "");
        String phne = Prefs.getString("Mail", "");

        un.setText(name);
        um.setText(phne);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart, menu);
        mcartMenuIcon = (LayerDrawable) menu.findItem(R.id.cart).getIcon();
        setBadgeCount(this, mcartMenuIcon, "");
        getAndSetCartCount();
        return true;
    }

    private void getAndSetCartCount() {
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
                if (rsp != null) {
                    mCartCount = rsp.getProducts().size();
                    setBadgeCount(HomeActivity.this, mcartMenuIcon, mCartCount + "");
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

        // Reuse drawable if possible
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

        int id = item.getItemId();
        switch (id) {
            case R.id.cart:
                Intent h = new Intent(HomeActivity.this, MyCartActivity.class);
                startActivity(h);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1000) {
            if (data != null) {
                String catId = data.getStringExtra("categoryid");

                if (catId != null)

                {
                    refresh(catId);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_categories) {

            Intent cat = new Intent(HomeActivity.this, CategoryList_Activity.class);
            startActivityForResult(cat, 1000);

        } else if (id == R.id.nav_library) {

            Intent lib = new Intent(HomeActivity.this, MyLibraryActivity.class);
            startActivity(lib);

        } else if (id == R.id.nav_community) {

            Intent com = new Intent(HomeActivity.this, MyCommunityActivity.class);
            startActivity(com);

        } else if (id == R.id.nav_cart) {

            Intent cart = new Intent(HomeActivity.this, MyCartActivity.class);
            startActivity(cart);

        } else if (id == R.id.nav_account) {

            Intent acc = new Intent(HomeActivity.this, MyAccountActivity.class);
            startActivity(acc);

        } else if (id == R.id.nav_orders) {

            Intent order = new Intent(HomeActivity.this, MyOrdersActivity.class);
            startActivity(order);

        } else if (id == R.id.nav_logout) {

            logout();
            Intent lgot = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(lgot);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        Prefs.edit();
        Prefs.remove("isregistered");
    }
}