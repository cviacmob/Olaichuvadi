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
import com.squareup.picasso.Picasso;

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
        public ImageView iv, rev, fav;
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
            holder.iv = (ImageView) purch.findViewById(R.id.lib_pur_img);
//            holder.rev = (ImageView) purch.findViewById(R.id.p_revbtn);
//            holder.fav = (ImageView) purch.findViewById(R.id.p_favbtn);
            purch.setTag(holder);
        } else {
            holder = (ViewHolder) purch.getTag();
        }
        holder.tv.setText(puinfo.getName());
        String url1 = mContext.getString(R.string.img_club);
        String url2 = puinfo.getImage();
        StringBuilder url3 = new StringBuilder();
        url3.append(url1 + url2);
        String url = url3.toString();
        if (!url.isEmpty()) {
            Picasso.with(purch.getContext()).load(url).placeholder(R.mipmap.log).resize(500, 500).into(holder.iv);
        }
 /*       holder.rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return purch;
    }
}