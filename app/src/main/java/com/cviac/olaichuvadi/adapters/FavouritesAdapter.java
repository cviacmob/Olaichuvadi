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

import org.w3c.dom.Text;

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
        ImageView iv;
        TextView tv;
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
            holder.tv = (TextView) fav.findViewById(R.id.book_title);
            fav.setTag(holder);
        } else {
            holder = (FavouritesAdapter.ViewHolder) fav.getTag();
        }
        holder.tv.setText(puinfo.getFav_tit());
        return fav;
    }
}