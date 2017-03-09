package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.Club_recInfo;

import java.util.List;

public class Club_recAdapter extends BaseAdapter {

    private Context mContext;
    private List<Club_recInfo> read;

    public Club_recAdapter(Context mContext, List<Club_recInfo> read) {
        this.mContext = mContext;
        this.read = read;
    }

    @Override
    public int getCount() {
        return read.size();
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
        public TextView tv1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View shr = view;
        Club_recAdapter.ViewHolder holder;
        Club_recInfo cinfo = read.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.club_item, null);
            holder = new ViewHolder();
            holder.tv1 = (TextView) shr.findViewById(R.id.clubtitle);
            shr.setTag(holder);
        } else {
            holder = (Club_recAdapter.ViewHolder) shr.getTag();
        }
        holder.tv1.setText(cinfo.getTitle());
        return shr;
    }
}