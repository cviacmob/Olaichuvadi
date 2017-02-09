package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.AuthorsInfo;

import java.util.List;

public class AuhtorsAdapter extends BaseAdapter {
    private Context mContext;
    private List<AuthorsInfo> authr;

    public AuhtorsAdapter(Context mContext, List<AuthorsInfo> authr) {
        this.mContext = mContext;
        this.authr = authr;
    }

    @Override
    public int getCount() {
        return authr.size();
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
        public ImageButton clk;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View shr = view;
        AuhtorsAdapter.ViewHolder holder;
        AuthorsInfo cinfo = authr.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.author_details, null);
            holder = new AuhtorsAdapter.ViewHolder();
            holder.iv = (ImageView) shr.findViewById(R.id.auhtr_img);
            holder.tv1 = (TextView) shr.findViewById(R.id.auhtr_tit);
            holder.clk = (ImageButton) shr.findViewById(R.id.likclk);
            shr.setTag(holder);
        } else {
            holder = (AuhtorsAdapter.ViewHolder) shr.getTag();
        }
        holder.tv1.setText(cinfo.getAuthor_tit());
        return shr;
    }
}