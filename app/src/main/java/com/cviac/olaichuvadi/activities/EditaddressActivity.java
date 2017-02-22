package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.AddressInfo;
import com.cviac.olaichuvadi.datamodels.Category;
import com.cviac.olaichuvadi.datamodels.CountryInfo;
import com.cviac.olaichuvadi.datamodels.GeneralResponse;
import com.cviac.olaichuvadi.datamodels.ZoneInfo;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.utilities.Prefs;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class EditaddressActivity extends AppCompatActivity {

    AddressInfo addrs;
    Button sub_btn, cncl_btn;
    EditText first_name, last_name, cmpny, addrs1, addrs2, city, pin_code;
    Spinner country, state;
    int c_id = Prefs.getInt("customer_id", -1);
    List<CountryInfo> countrieslist;
    List<ZoneInfo> stateslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaddress);
        loadspinner();

        countrieslist = new ArrayList<CountryInfo>();
        stateslist = new ArrayList<ZoneInfo>();

        first_name = (EditText) findViewById(R.id.fname_ed);
        last_name = (EditText) findViewById(R.id.lname_ed);
        cmpny = (EditText) findViewById(R.id.cmpny_ed);
        addrs1 = (EditText) findViewById(R.id.addr1_ed);
        addrs2 = (EditText) findViewById(R.id.addr2_ed);
        city = (EditText) findViewById(R.id.city_ed);
        pin_code = (EditText) findViewById(R.id.pin_ed);
        country = (Spinner) findViewById(R.id.country);
        state = (Spinner) findViewById(R.id.state);

        sub_btn = (Button) findViewById(R.id.sub_btn);
        cncl_btn = (Button) findViewById(R.id.cncl_btn);

        addrs = (AddressInfo) getIntent().getSerializableExtra("Address");

        if (addrs != null) {

            first_name.setText(addrs.getFirstname(), TextView.BufferType.EDITABLE);
            last_name.setText(addrs.getLastname(), TextView.BufferType.EDITABLE);
            cmpny.setText(addrs.getCompany(), TextView.BufferType.EDITABLE);
            addrs1.setText(addrs.getAddress_1(), TextView.BufferType.EDITABLE);
            addrs2.setText(addrs.getAddress_2(), TextView.BufferType.EDITABLE);
            city.setText(addrs.getCity(), TextView.BufferType.EDITABLE);
            pin_code.setText(addrs.getPostcode(), TextView.BufferType.EDITABLE);

            sub_btn.setText(R.string.btn_save);
            setTitle("Edit Your Address Here");
        } else {
            sub_btn.setText(R.string.btn_sub);
            setTitle("Enter Your Address Here");
        }


        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = first_name.getText().toString();
                String lname = last_name.getText().toString();
                String company = cmpny.getText().toString();
                String addres1 = addrs1.getText().toString();
                String addres2 = addrs2.getText().toString();
                String cty = city.getText().toString();
                String pin = pin_code.getText().toString();

                CountryInfo cntry = (CountryInfo) country.getSelectedItem();
                String country_id = cntry.getCountry_id();
                ZoneInfo stt = (ZoneInfo) state.getSelectedItem();
                String state_id = stt.getZone_id();

                if (fname.length() < 1) {
                    first_name.setError("Enter Valid First Name");
                    return;
                } else if (lname.length() < 1) {
                    last_name.setError("Enter Valid Last Name");
                    return;
                } else if (addres1.length() < 1) {
                    addrs1.setError("Enter Valid Address");
                    return;
                } else if (addres2.length() < 1) {
                    addrs2.setError("Enter Valid Address");
                    return;
                } else if (cty.length() < 1) {
                    city.setError("Enter Valid City");
                    return;
                } else if (pin.length() < 1) {
                    pin_code.setError("Enter Valid PIN Code");
                    return;
                }

                if (addrs != null) {
                    String addrs_id = addrs.getAddress_id();
                    editAddress(addrs_id, c_id + "", fname, lname, company, addres1, addres2, pin, cty, state_id, country_id);
                } else {
                    addAddress(c_id + "", fname, lname, company, addres1, addres2, pin, cty, state_id, country_id);
                }
            }
        });
        cncl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void editAddress(String addrs_id, String s, String fname, String lname, String company, String addres1, String addres2, String pin, String cty, String state_id, String country_id) {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        Call<GeneralResponse> call = api.editAddress(addrs_id, c_id + "", fname, lname, company, addres1, addres2, pin, cty, state_id, country_id);
        call.enqueue(new Callback<GeneralResponse>() {

            public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                GeneralResponse rsp1 = response.body();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void addAddress(String s, String fname, String lname, String company, String addres1, String addres2, String pin, String cty, String state_id, String country_id) {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        Call<GeneralResponse> call = api.addAddress(c_id + "", fname, lname, company, addres1, addres2, pin, cty, state_id, country_id);
        call.enqueue(new Callback<GeneralResponse>() {

            public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {

                GeneralResponse rsp2 = response.body();
                if (rsp2.getCode() == 0) {

                    Intent new_address = new Intent();

                    setResult(140, new_address);
                    finish();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadspinner() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        Call<List<CountryInfo>> call = api.getCountries();
        call.enqueue(new Callback<List<CountryInfo>>() {
            @Override
            public void onResponse(Response<List<CountryInfo>> response, Retrofit retrofit) {

                List<CountryInfo> contr = response.body();
                countrieslist.clear();
                countrieslist.addAll(contr);

                ArrayAdapter adapter1 = new ArrayAdapter(EditaddressActivity.this, android.R.layout.simple_spinner_item, countrieslist);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                country.setAdapter(adapter1);

                country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        CountryInfo cinfo = (CountryInfo) country.getSelectedItem();
                        String c_id = cinfo.getCountry_id();

                        OkHttpClient okHttpClient = new OkHttpClient();
                        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
                        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://nheart.cviac.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .client(okHttpClient)
                                .build();

                        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

                        Call<List<ZoneInfo>> call = api.getZones(c_id);
                        call.enqueue(new Callback<List<ZoneInfo>>() {
                            @Override
                            public void onResponse(Response<List<ZoneInfo>> response, Retrofit retrofit) {

                                List<ZoneInfo> zone = response.body();
                                stateslist.clear();
                                stateslist.addAll(zone);

                                ArrayAdapter adapter2 = new ArrayAdapter(EditaddressActivity.this, android.R.layout.simple_spinner_item, stateslist);
                                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                state.setAdapter(adapter2);
                            }

                            @Override
                            public void onFailure(Throwable t) {
                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

}