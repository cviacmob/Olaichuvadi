package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.ReviewedInfo;

import java.util.List;

public class ReviewedAdapter extends BaseAdapter {

    private Context mContext;
    private List<ReviewedInfo> lib_rev;

    public ReviewedAdapter(Context mContext, List<ReviewedInfo> lib_rev) {
        this.mContext = mContext;
        this.lib_rev = lib_rev;
    }

    @Override
    public int getCount() {
        return lib_rev.size();
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
        public ImageView iv;
        public TextView tv1, tv2, tv3;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View revv = view;
        ReviewedAdapter.ViewHolder holder;
        ReviewedInfo puinfo = lib_rev.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            revv = inflater.inflate(R.layout.revieweditem, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) revv.findViewById(R.id.rev_img);
            holder.tv1 = (TextView) revv.findViewById(R.id.rev_tt);
            holder.tv2 = (TextView) revv.findViewById(R.id.rev_auth);
            holder.tv3 = (TextView) revv.findViewById(R.id.revv);
            revv.setTag(holder);
        } else {
            holder = (ReviewedAdapter.ViewHolder) revv.getTag();
        }
        holder.iv.setImageResource(puinfo.getRev_books());
        holder.tv1.setText(puinfo.getRev_tit());
        holder.tv2.setText(puinfo.getRev_auth());
        return revv;
    }
}