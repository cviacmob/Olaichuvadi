package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.ReadingClubInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Club_recAdapter extends BaseAdapter {

    private Context mContext;
    private List<ReadingClubInfo> read;

    public Club_recAdapter(Context mContext, List<ReadingClubInfo> read) {
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
        public ImageView iv;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View shr = view;
        Club_recAdapter.ViewHolder holder;
        ReadingClubInfo cinfo = read.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.club_item, null);
            holder = new ViewHolder();
            holder.tv1 = (TextView) shr.findViewById(R.id.clubtitle);
            holder.iv = (ImageView) shr.findViewById(R.id.club_img);
            shr.setTag(holder);
        } else {
            holder = (Club_recAdapter.ViewHolder) shr.getTag();
        }
        holder.tv1.setText(cinfo.getGroup_name());
        String url = cinfo.getGroup_image();
        if (!url.isEmpty()) {
            Picasso.with(shr.getContext()).load(url).placeholder(R.mipmap.bookgrd).resize(500, 500).into(holder.iv);
        }
        return shr;
    }
}