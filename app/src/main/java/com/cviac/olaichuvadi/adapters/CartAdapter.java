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
import com.cviac.olaichuvadi.datamodels.CartInfo;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    private Context mContext;
    private List<CartInfo> crtitm;

    public CartAdapter(Context mContext, List<CartInfo> crtitm) {
        this.mContext = mContext;
        this.crtitm = crtitm;
    }

    @Override
    public int getCount() {
        return crtitm.size();
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
        public ImageButton del;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View crt = view;
        ViewHolder holder;
        CartInfo cinfo = crtitm.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            crt = inflater.inflate(R.layout.cartitems, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) crt.findViewById(R.id.crtimg);
            holder.tv1 = (TextView) crt.findViewById(R.id.crttit);
            holder.tv2 = (TextView) crt.findViewById(R.id.crtdesc);
            holder.tv3 = (TextView) crt.findViewById(R.id.crtprc);
            holder.del = (ImageButton) crt.findViewById(R.id.delbtn);
            crt.setTag(holder);
        } else {
            holder = (ViewHolder) crt.getTag();
        }
        holder.iv.setImageResource(cinfo.getPrdimg());
        holder.tv1.setText(cinfo.getTitle());
        holder.tv2.setText(cinfo.getDesc());
        holder.tv3.setText(cinfo.getPrice());
        return crt;
    }
}
