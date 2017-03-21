package com.cviac.olaichuvadi.activities;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.ProductsAdapter;
import com.cviac.olaichuvadi.datamodels.CategoriesResponse;
import com.cviac.olaichuvadi.datamodels.Category;
import com.cviac.olaichuvadi.datamodels.CategoryProductsResponse;
import com.cviac.olaichuvadi.datamodels.GetCartItemsResponse;
import com.cviac.olaichuvadi.datamodels.LoginResponse;
import com.cviac.olaichuvadi.datamodels.Product;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.cviac.olaichuvadi.utilities.BadgeDrawable;
import com.cviac.olaichuvadi.utilities.Prefs;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private Locale myLocale;
    private SliderLayout imageSlider;
    GridView gv;
    List<Product> rowListItem;
    ProductsAdapter adapter;
    private BroadcastReceiver listenCartChange;
    private LayerDrawable mcartMenuIcon;
    private int mCartCount = 0;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rowListItem = new ArrayList<Product>();

        gv = (GridView) findViewById(R.id.prdts);
        gv.setFastScrollEnabled(true);
        adapter = new ProductsAdapter(HomeActivity.this, rowListItem);
        gv.setAdapter(adapter);

        imageSlider = (SliderLayout) findViewById(R.id.slider);

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

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/bamini.TTF");

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

        final Call<CategoriesResponse> call = api.getCategories();
        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Response<CategoriesResponse> response, Retrofit retrofit) {

                CategoriesResponse rsp = response.body();
                List<Category> catlist = rsp.getCategories();
                String feature_id = "";
                String promoted_id = "";
                for (Category cat : catlist) {
                    if (cat.getName().equalsIgnoreCase("featured")) {
                        feature_id = cat.getCategory_id();
                    } else if (cat.getName().equalsIgnoreCase("promoted")) {
                        promoted_id = cat.getCategory_id();
                    }
                }
                if (!promoted_id.isEmpty()) {
                    slider(promoted_id);
                }
                if (!feature_id.isEmpty()) {
                    refresh(feature_id);
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

        listenCartChange = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                getAndSetCartCount();
            }
        };

        registerReceiver(listenCartChange, new IntentFilter("notifyCartChange"));
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        unregisterReceiver(listenCartChange);
    }

    private void getSetToken() {
        String token = Prefs.getString("token", null);
        if (token == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getString(R.string.baseurl))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            OpencartAPIs api = retrofit.create(OpencartAPIs.class);

            String apiKey = "3wDBJPRQrsL2hPOPlKXMOEhMQNLas6vb5aDpqiu9M2d5f13BUBXcvE8FpDn1D7yO8nPodmkrBifAzhcGUWrTyfZBbrCYiFp03u2DwmTGB4kPKihUCt199btO1i1gGPShxaoMlcuf1zcrMYmc2Z8zQqdnphpIwbA7RORYn6xjUgvGtUAL4yJ709cxkni6dDF7gkCRformUVrefpLhXkuOVa8Yd3W3ausZ3KHDctUpWovATSqsxLI3pRSc89PfjXUM";
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

        final Call<CategoryProductsResponse> call = api.getProducts(catId);

        call.enqueue(new Callback<CategoryProductsResponse>() {
            @Override
            public void onResponse(Response<CategoryProductsResponse> response, Retrofit retrofit) {
                CategoryProductsResponse rsp = response.body();
                rowListItem.clear();
                rowListItem.addAll(rsp.getProducts());
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

    public void slider(String catId) {
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

        //String credentials = Credentials.basic("olaichuvadi", "cviac");

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        final Call<CategoryProductsResponse> call = api.getProducts(catId);
        call.enqueue(new Callback<CategoryProductsResponse>() {
            @Override
            public void onResponse(Response<CategoryProductsResponse> response, Retrofit retrofit) {
                CategoryProductsResponse rsp = response.body();

                for (int i = 0; i < rsp.getProducts().size(); i++) {

                    Product prdct = rsp.getProducts().get(i);

                    TextSliderView textSliderView = new TextSliderView(HomeActivity.this);

                    // initialize a SliderLayout
                    textSliderView
                            .description(prdct.getName())
                            .image(prdct.getThumb())
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(HomeActivity.this);

                    Bundle data = new Bundle();
                    data.putSerializable("Prdobj", prdct);

                    textSliderView.bundle(data);

                    imageSlider.addSlider(textSliderView);
                }

                imageSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                imageSlider.setCustomAnimation(new DescriptionAnimation());
                imageSlider.addOnPageChangeListener(HomeActivity.this);
            }

            @Override
            public void onFailure(Throwable t) {
            }

        });
    }

    private void setUserdetails(NavigationView navigationView) {

        View hview = navigationView.getHeaderView(0);

        TextView un = (TextView) hview.findViewById(R.id.nav_name);
        TextView um = (TextView) hview.findViewById(R.id.nav_mail);

        String name = Prefs.getString("Regname", "User_Name");
        String phne = Prefs.getString("Regmail", "User_Email");

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

        SearchView search = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchQueryActivity.class)));
        search.setQueryHint(getResources().getString(R.string.search_hint));
        String query = search.getQuery().toString();

        return true;
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
    protected void onStop() {
        imageSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Bundle datas = slider.getBundle();
        Product prd = (Product) datas.getSerializable("Prdobj");

        Intent prdss = new Intent(HomeActivity.this, Product_Details.class);
        prdss.putExtra("productobj", prd);
        startActivity(prdss);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.cart:
                Intent cart = new Intent(HomeActivity.this, MyCartActivity.class);
                startActivity(cart);
                break;
            case R.id.tamil:
                setLocale("ta");
                break;
            case R.id.english:
                setLocale("en");
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

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, HomeActivity.class);
        startActivity(refresh);
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_categories) {

            Intent cat = new Intent(HomeActivity.this, CategoryList_Activity.class);
            startActivityForResult(cat, 1000);

        } else if (id == R.id.nav_lib_books) {

            Intent libb = new Intent(HomeActivity.this, MyLib_Books.class);
            startActivity(libb);

        } else if (id == R.id.nav_lib_prchsd) {

            Intent libb = new Intent(HomeActivity.this, MyLib_Purchased.class);
            startActivity(libb);

        } else if (id == R.id.nav_lib_rev) {

            Intent libb = new Intent(HomeActivity.this, MyLib_Reviewed.class);
            startActivity(libb);

        } else if (id == R.id.nav_lib_fav) {

            Intent libb = new Intent(HomeActivity.this, MyLib_Favourites.class);
            startActivity(libb);

        } else if (id == R.id.nav_com_shared) {

            Intent libb = new Intent(HomeActivity.this, MyComm_Shared.class);
            startActivity(libb);

        } else if (id == R.id.nav_com_club) {

            Intent libb = new Intent(HomeActivity.this, MyComm_Readingclub.class);
            startActivity(libb);

        } else if (id == R.id.nav_com_auth) {

            Intent libb = new Intent(HomeActivity.this, MyComm_Authors.class);
            startActivity(libb);

        } else if (id == R.id.nav_com_publ) {

            Intent libb = new Intent(HomeActivity.this, MyComm_Publ.class);
            startActivity(libb);

        } else if (id == R.id.nav_cart) {

            Intent cart = new Intent(HomeActivity.this, MyCartActivity.class);
            startActivity(cart);

        } else if (id == R.id.nav_account) {

            Intent acc = new Intent(HomeActivity.this, MyAccountActivity.class);
            startActivity(acc);

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