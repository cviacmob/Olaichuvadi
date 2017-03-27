package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.PurchasedBooksInfo;

import java.util.List;

public class PurchasedAdapter extends BaseAdapter {

    private Context mContext;
    private List<PurchasedBooksInfo> lib_pur;

    public PurchasedAdapter(Context mContext, List<PurchasedBooksInfo> lib_pur) {
        this.mContext = mContext;
        this.lib_pur = lib_pur;
    }

    @Override
    public int getCount() {
        return lib_pur.size();
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
        public TextView tv;
        public ImageView rev, fav;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View purch = view;
        ViewHolder holder;
        PurchasedBooksInfo puinfo = lib_pur.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            purch = inflater.inflate(R.layout.purchaseditem, null);
            holder = new ViewHolder();
            holder.tv = (TextView) purch.findViewById(R.id.purchtit);
            holder.rev = (ImageView) purch.findViewById(R.id.p_revbtn);
            holder.fav = (ImageView) purch.findViewById(R.id.p_favbtn);
            purch.setTag(holder);
        } else {

            holder = (ViewHolder) purch.getTag();
        }
        return purch;
    }
}