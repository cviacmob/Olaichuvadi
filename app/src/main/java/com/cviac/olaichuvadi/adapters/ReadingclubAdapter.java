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
import com.cviac.olaichuvadi.datamodels.ReadingclubInfo;

import java.util.List;

public class ReadingclubAdapter extends BaseAdapter {

    private Context mContext;
    private List<ReadingclubInfo> read;

    public ReadingclubAdapter(Context mContext, List<ReadingclubInfo> read) {
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
        public ImageView iv;
        public TextView tv1;
        public Button join;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View shr = view;
        ReadingclubAdapter.ViewHolder holder;
        ReadingclubInfo cinfo = read.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.club_item, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) shr.findViewById(R.id.club_img);
            holder.tv1 = (TextView) shr.findViewById(R.id.clubtit);
            holder.join = (Button) shr.findViewById(R.id.joinbtn);
            shr.setTag(holder);
        } else {
            holder = (ReadingclubAdapter.ViewHolder) shr.getTag();
        }
        holder.iv.setImageResource(cinfo.getClub_img());
        holder.tv1.setText(cinfo.getTitle());

        return shr;
    }
}
