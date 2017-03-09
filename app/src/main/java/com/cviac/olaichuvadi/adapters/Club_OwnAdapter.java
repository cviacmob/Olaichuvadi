package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.Club_recInfo;

import java.util.List;

public class Club_OwnAdapter extends BaseAdapter {

    private Context mContext;
    private List<Club_recInfo> read;

    public Club_OwnAdapter(Context mContext, List<Club_recInfo> read) {
        this.mContext = mContext;
        this.read = read;
    }

    @Override
    public int getCount() {
        return read.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public ImageView iv;
        public TextView tv1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View club = convertView;
        Club_OwnAdapter.ViewHolder holder;
        Club_recInfo cinfo = read.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            club = inflater.inflate(R.layout.activity_club__own_adapter, null);
            holder = new Club_OwnAdapter.ViewHolder();
            holder.iv = (ImageView) club.findViewById(R.id.clubimg);
            holder.tv1 = (TextView) club.findViewById(R.id.club);
            club.setTag(holder);
        } else {
            holder = (Club_OwnAdapter.ViewHolder) club.getTag();
        }
        holder.tv1.setText(cinfo.getTitle());
        return club;
    }
}