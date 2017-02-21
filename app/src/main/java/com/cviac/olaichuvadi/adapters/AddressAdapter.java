package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.activities.MyAccountActivity;
import com.cviac.olaichuvadi.datamodels.AddressInfo;
import com.cviac.olaichuvadi.datamodels.GeneralResponse;
import com.cviac.olaichuvadi.services.OpencartAPIs;
import com.cviac.olaichuvadi.utilities.Prefs;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class AddressAdapter extends BaseAdapter {

    private MyAccountActivity mContext;
    private List<AddressInfo> additm;

    public AddressAdapter(MyAccountActivity mContext, List<AddressInfo> additm) {
        this.mContext = mContext;
        this.additm = additm;
    }

    @Override
    public int getCount() {
        return additm.size();
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
        public TextView tv1, tv2, tv4, tv5, tv6, tv7, tv8;
        public ImageButton edt, del;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View ord = view;
        AddressAdapter.ViewHolder holder;
        final AddressInfo adinfo = additm.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ord = inflater.inflate(R.layout.addr_layout, null);
            holder = new AddressAdapter.ViewHolder();
            holder.tv1 = (TextView) ord.findViewById(R.id.addname);
            holder.tv2 = (TextView) ord.findViewById(R.id.addlname);
            holder.tv4 = (TextView) ord.findViewById(R.id.addr1);
            holder.tv5 = (TextView) ord.findViewById(R.id.addr2);
            holder.tv6 = (TextView) ord.findViewById(R.id.city);
            holder.tv7 = (TextView) ord.findViewById(R.id.stat);
            holder.tv8 = (TextView) ord.findViewById(R.id.zip);
            holder.del = (ImageButton) ord.findViewById(R.id.delbtn);
            holder.edt = (ImageButton) ord.findViewById(R.id.editaddr);
            ord.setTag(holder);
        } else {
            holder = (ViewHolder) ord.getTag();
        }
        holder.tv1.setText(adinfo.getFirstname());
        holder.tv2.setText(adinfo.getLastname());
        holder.tv4.setText(adinfo.getAddress_1());
        holder.tv5.setText(adinfo.getAddress_2());
        holder.tv6.setText(adinfo.getCity());
        holder.tv7.setText(adinfo.getZone());
        holder.tv8.setText(adinfo.getPostcode());

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add_id = adinfo.getAddress_id();
                int c_id = Prefs.getInt("customer_id", -1);
                deleteAddress(add_id, c_id + "");
            }
        });
        holder.edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add_id = adinfo.getAddress_id();
                int c_id = Prefs.getInt("customer_id", -1);

                ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> hashfields = new HashMap<String, String>();

                hashfields.put("First_Name", adinfo.getFirstname());
                hashfields.put("Last_Name", adinfo.getLastname());
                hashfields.put("Company", adinfo.getCompany());
                hashfields.put("Address_1", adinfo.getAddress_1());
                hashfields.put("Address_2", adinfo.getAddress_2());
                hashfields.put("City", adinfo.getCity());
                hashfields.put("Pin_Code", adinfo.getPostcode());
                hashfields.put("State", adinfo.getZone());
                hashfields.put("Country", adinfo.getCountry());

                arrayList.add(hashfields);

                editAddress(add_id, c_id + " ", hashfields);
            }
        });

        return ord;
    }

    private void deleteAddress(String address_id, String customer_id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        Call<GeneralResponse> call = api.deleteAddress(address_id, customer_id);
        call.enqueue(new Callback<GeneralResponse>() {

            public void onResponse(Response<GeneralResponse> response, Retrofit retrofit) {
                GeneralResponse resp = response.body();

                if (resp.getCode() == 0) {
                    mContext.loadAddresses();
                } else {
                    Toast.makeText(mContext, "Address Deletion Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void editAddress(String address_id, String cust_id, HashMap<String, String> hashfields) {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nheart.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        OpencartAPIs api = retrofit.create(OpencartAPIs.class);

        Call<List<AddressInfo>> call = api.editAddress(address_id, cust_id, hashfields);
        call.enqueue(new Callback<List<AddressInfo>>() {

            public void onResponse(Response<List<AddressInfo>> response, Retrofit retrofit) {
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}