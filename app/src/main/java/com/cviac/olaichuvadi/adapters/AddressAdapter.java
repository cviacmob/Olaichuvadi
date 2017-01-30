package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.AddressInfo;

import java.util.List;

public class AddressAdapter extends BaseAdapter {

    private Context mContext;
    private List<AddressInfo> additm;

    public AddressAdapter(Context mContext, List<AddressInfo> orditm) {
        this.mContext = mContext;
        this.additm = orditm;
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
        public TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
        public ImageButton del;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View ord = view;
        AddressAdapter.ViewHolder holder;
        AddressInfo adinfo = additm.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ord = inflater.inflate(R.layout.addr_layout, null);
            holder = new AddressAdapter.ViewHolder();
            holder.tv1 = (TextView) ord.findViewById(R.id.addname);
            holder.tv2 = (TextView) ord.findViewById(R.id.addlname);
            holder.tv3 = (TextView) ord.findViewById(R.id.drst);
            holder.tv4 = (TextView) ord.findViewById(R.id.cty);
            holder.tv5 = (TextView) ord.findViewById(R.id.dist);
            holder.tv6 = (TextView) ord.findViewById(R.id.stt);
            holder.tv7 = (TextView) ord.findViewById(R.id.pin);
            holder.tv8 = (TextView) ord.findViewById(R.id.mbl);
            holder.del = (ImageButton) ord.findViewById(R.id.delbtn);
            ord.setTag(holder);
        } else {
            holder = (ViewHolder) ord.getTag();
        }
        holder.tv1.setText(adinfo.getFname());
        holder.tv2.setText(adinfo.getLname());
        holder.tv3.setText(adinfo.getAddr());
        holder.tv4.setText(adinfo.getCity());
        holder.tv5.setText(adinfo.getDist());
        holder.tv6.setText(adinfo.getState());
        holder.tv7.setText(adinfo.getPin_code());
        holder.tv8.setText(adinfo.getMobileno());
        return ord;
    }
}