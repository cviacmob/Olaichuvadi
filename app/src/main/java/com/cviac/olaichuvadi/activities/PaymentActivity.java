package com.cviac.olaichuvadi.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.PaymentAdapter;
import com.cviac.olaichuvadi.datamodels.AddressInfo;
import com.cviac.olaichuvadi.datamodels.CartTotalInfo;
import com.cviac.olaichuvadi.datamodels.GeneralResponse;
import com.cviac.olaichuvadi.datamodels.GetCartItemsResponse;
import com.cviac.olaichuvadi.datamodels.PaymentMethodsResponse;
import com.cviac.olaichuvadi.datamodels.ProductCartInfo;
import com.cviac.olaichuvadi.datamodels.ShippingMethodsResponse;
import com.cviac.olaichuvadi.services.AddCookiesInterceptor;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.services.ReceivedCookiesInterceptor;
import com.cviac.olaichuvadi.utilities.NonScrollListView;
import com.cviac.olaichuvadi.utilities.Prefs;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PaymentActivity extends AppCompatActivity {

    AddressInfo pay_addr;
    AddressInfo ship_addr;
    ProgressDialog progressDialog = null;
    PaymentAdapter adapter;
    List<ProductCartInfo> cartProducts;
    List<CartTotalInfo> cartTotals;
    NonScrollListView nonScrollListView;
    Button pay;
    TextView amount, tv1, tv2, tv3, tv4, tv5, tv6, tv7;
    ImageView iv;
    String paymethod = "cod";
    String shipmethod = "flat";
    List<AddressInfo> addrlist;
    AlertDialog levelDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle(getString(R.string.tit_pay));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartProducts = new ArrayList<>();

        loadCartItems();
        loadAddresses();
        /*loadPaymentMethods();
        loadShippingMethods();*/
        nonScrollListView = (NonScrollListView) findViewById(R.id.crtitms);
        adapter = new PaymentAdapter(PaymentActivity.this, cartProducts);
        nonScrollListView.setAdapter(adapter);
        amount = (TextView) findViewById(R.id.priceamnt);

        tv1 = (TextView) findViewById(R.id.payname);
        tv2 = (TextView) findViewById(R.id.payaddr1);
        tv3 = (TextView) findViewById(R.id.payaddr2);
        tv4 = (TextView) findViewById(R.id.payCity);
        tv5 = (TextView) findViewById(R.id.payPIN);
        tv6 = (TextView) findViewById(R.id.payState);
        tv7 = (TextView) findViewById(R.id.payCountry);

        iv = (ImageView) findViewById(R.id.edtadd);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                builder.setTitle("Select Address");
                ArrayAdapter adapter1 = new ArrayAdapter(PaymentActivity.this, android.R.layout.select_dialog_singlechoice, addrlist);
                builder.setSingleChoiceItems(adapter1, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        levelDialog.dismiss();
                        if (addrlist != null && addrlist.size() > 0) {
                            pay_addr = addrlist.get(item);
                            ship_addr = addrlist.get(item);

                            setAddress(addrlist.get(item));
                        }
                    }
                });
                levelDialog = builder.create();
                levelDialog.show();
            }
        });

        pay = (Button) findViewById(R.id.paybutn);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkOut();
            }
        });
    }

    private void loadShippingMethods() {

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

        int c_id = Prefs.getInt("customer_id", -1);
        Call<ShippingMethodsResponse> call = api.getShippingMethods();
        call.enqueue(new Callback<ShippingMethodsResponse>() {

            public void onResponse(Response<ShippingMethodsResponse> response, Retrofit retrofit) {
                ShippingMethodsResponse rsp = response.body();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadPaymentMethods() {

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

        int c_id = Prefs.getInt("customer_id", -1);
        Call<PaymentMethodsResponse> call = api.getPaymentMethods();
        call.enqueue(new Callback<PaymentMethodsResponse>() {

            public void onResponse(Response<PaymentMethodsResponse> response, Retrofit retrofit) {
                PaymentMethodsResponse rsp = response.body();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void loadCartItems() {
        progressDialog = new ProgressDialog(PaymentActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

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
                progressDialog.dismiss();
                GetCartItemsResponse rsp = response.body();
                cartProducts.clear();
                cartProducts.addAll(rsp.getProducts());
                adapter.notifyDataSetInvalidated();
                cartTotals = rsp.getTotals();
                amount.setText(cartTotals.get(cartTotals.size() - 1).getText());
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    public void loadAddresses() {

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

        int c_id = Prefs.getInt("customer_id", -1);
        Call<List<AddressInfo>> call = api.getAdresses(c_id + "");
        call.enqueue(new Callback<List<AddressInfo>>() {

            public void onResponse(Response<List<AddressInfo>> response, Retrofit retrofit) {
                List<AddressInfo> rsp = response.body();
                addrlist = rsp;
                if (addrlist != null && addrlist.size() > 0) {
                    AddressInfo info = addrlist.get(0);
                    setAddress(info);
                    pay_addr = info;
                    ship_addr = info;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setAddress(AddressInfo info) {

        tv1.setText(info.getFirstname());
        tv2.setText(info.getAddress_1());
        tv3.setText(info.getAddress_2());
        tv4.setText(info.getCity());
        tv5.setText(info.getPostcode());
        tv6.setText(info.getZone());
        tv7.setText(info.getCountry());

    }

    private void checkOut() {
        setCustmoerSession();
    }

    private void setCustmoerSession() {

        String cus_id = Prefs.getInt("customer_id", -1) + "";
        String firstname = Prefs.getString("Regname", "");
        String email = Prefs.getString("Regmail", "");
        String telephone = Prefs.getString("Regphone", "");

        progressDialog = new ProgressDialog(PaymentActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

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

        Call<GeneralResponse> call = api.setCustomerSession(cus_id, "1", firstname, firstname, email, telephone, "");
        call.enqueue(new Callback<GeneralResponse>() {

            public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                GeneralResponse rsp = response.body();
                if (rsp.getSuccess() != null && !rsp.getSuccess().isEmpty()) {
                    setPaymentAddress();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(PaymentActivity.this, "Place Order - Customer Session Error" + rsp.getError(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, "Place Order Error - Set Customer Session", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });

    }

    private void setPaymentAddress() {
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

        Call<GeneralResponse> call = api.setPaymentAddress(pay_addr.getFirstname(), pay_addr.getLastname(),
                pay_addr.getCompany(), pay_addr.getAddress_1(),
                pay_addr.getAddress_2(), pay_addr.getPostcode(),
                pay_addr.getCity(), pay_addr.getZone_id(), pay_addr.getCountry_id());
        call.enqueue(new Callback<GeneralResponse>() {

            public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                GeneralResponse rsp = response.body();
                if (rsp.getSuccess() != null && !rsp.getSuccess().isEmpty()) {
                    getPaymentMethods();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(PaymentActivity.this, "Place Order - Payment Address Error" + rsp.getError(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, "Place Order Error - Set Payment Address", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void getPaymentMethods() {

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

        int c_id = Prefs.getInt("customer_id", -1);
        Call<PaymentMethodsResponse> call = api.getPaymentMethods();
        call.enqueue(new Callback<PaymentMethodsResponse>() {

            public void onResponse(Response<PaymentMethodsResponse> response, Retrofit retrofit) {
                PaymentMethodsResponse rsp = response.body();
                setPaymentMethod();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, "Place Order - Get Payment Methods Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setPaymentMethod() {
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

        Call<GeneralResponse> call = api.setPaymentMethod(paymethod);
        call.enqueue(new Callback<GeneralResponse>() {

            public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                GeneralResponse rsp = response.body();
                if (rsp.getSuccess() != null && !rsp.getSuccess().isEmpty()) {
                    setShippingAddress();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(PaymentActivity.this, "Place Order - Payment Method Error:" + rsp.getError(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, "Place Order Error - Set Payment Method", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void setShippingAddress() {

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

        Call<GeneralResponse> call = api.setShippingAddress(ship_addr.getFirstname(), ship_addr.getLastname(),
                ship_addr.getCompany(), ship_addr.getAddress_1(),
                ship_addr.getAddress_2(), ship_addr.getPostcode(),
                ship_addr.getCity(), ship_addr.getZone_id(), ship_addr.getCountry_id());
        call.enqueue(new Callback<GeneralResponse>() {

            public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                GeneralResponse rsp = response.body();
                if (rsp.getSuccess() != null && !rsp.getSuccess().isEmpty()) {
                    getShippingMethods();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(PaymentActivity.this, "Place Order - Shipping Address Error" + rsp.getError(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, "Place Order Error - Set Shipping Address", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void getShippingMethods() {

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

        int c_id = Prefs.getInt("customer_id", -1);
        Call<ShippingMethodsResponse> call = api.getShippingMethods();
        call.enqueue(new Callback<ShippingMethodsResponse>() {

            public void onResponse(Response<ShippingMethodsResponse> response, Retrofit retrofit) {
                ShippingMethodsResponse rsp = response.body();
                setShippingMethod();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setShippingMethod() {

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

        Call<GeneralResponse> call = api.setShippingMethod(shipmethod);
        call.enqueue(new Callback<GeneralResponse>() {

            public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                GeneralResponse rsp = response.body();
                if (rsp.getSuccess() != null && !rsp.getSuccess().isEmpty()) {
                    placeOrder();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(PaymentActivity.this, "Place Order - Shipping Method Error" + rsp.getError(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, "Place Order Error - Set Shipping Method", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void placeOrder() {

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

        Call<ResponseBody> call = api.placeOrder(paymethod, "flat.flat", "test", "1", "1");
        call.enqueue(new Callback<ResponseBody>() {

            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                progressDialog.dismiss();
                ResponseBody rsp = response.body();
                Toast.makeText(PaymentActivity.this, "Placed Order Successfully", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, "Place Order Failure", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}