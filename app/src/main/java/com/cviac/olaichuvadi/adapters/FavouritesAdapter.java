package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.FavouritesInfo;

import java.util.List;

public class FavouritesAdapter extends BaseAdapter {
    private Context mContext;
    private List<FavouritesInfo> favv;

    public FavouritesAdapter(Context mContext, List<FavouritesInfo> favv) {
        this.mContext = mContext;
        this.favv = favv;
    }

    @Override
    public int getCount() {
        return favv.size();
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
        public TextView tv1, tv2, tv3, tv4;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View fav = view;
        FavouritesAdapter.ViewHolder holder;
        FavouritesInfo puinfo = favv.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            fav = inflater.inflate(R.layout.favouritesitem, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) fav.findViewById(R.id.fav_imggg);
            holder.tv1 = (TextView) fav.findViewById(R.id.titl);
            holder.tv2 = (TextView) fav.findViewById(R.id.tit2);
            holder.tv3 = (TextView) fav.findViewById(R.id.auth1);
            holder.tv4 = (TextView) fav.findViewById(R.id.auth2);
            fav.setTag(holder);
        } else {
            holder = (FavouritesAdapter.ViewHolder) fav.getTag();
        }
        holder.iv.setImageResource(puinfo.getFav_img());
        holder.tv2.setText(puinfo.getFav_tit());
        holder.tv4.setText(puinfo.getFav_auth());
        return fav;
    }
}
