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
import com.cviac.olaichuvadi.datamodels.ReadingClubInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Club_OwnAdapter extends BaseAdapter {

    private Context mContext;
    private List<ReadingClubInfo> club;

    public Club_OwnAdapter(Context mContext, List<ReadingClubInfo> club) {
        this.mContext = mContext;
        this.club = club;
    }

    @Override
    public int getCount() {
        return club.size();
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
        View clubs = convertView;
        Club_OwnAdapter.ViewHolder holder;
        ReadingClubInfo cinfo = club.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            clubs = inflater.inflate(R.layout.activity_club__own_adapter, null);
            holder = new Club_OwnAdapter.ViewHolder();
            holder.iv = (ImageView) clubs.findViewById(R.id.clubimg);
            holder.tv1 = (TextView) clubs.findViewById(R.id.club);
            clubs.setTag(holder);
        } else {
            holder = (Club_OwnAdapter.ViewHolder) clubs.getTag();
        }
        holder.tv1.setText(cinfo.getGroup_name());

        String url1 = mContext.getString(R.string.img_club);
        String url2 = cinfo.getGroup_image();
        StringBuilder url3 = new StringBuilder();
        url3.append(url1 + url2);
        String url = url3.toString();
        if (!url.isEmpty()) {
            Picasso.with(clubs.getContext()).load(url).placeholder(R.mipmap.bookgrd).resize(500, 500).into(holder.iv);
        }
        return clubs;
    }
}