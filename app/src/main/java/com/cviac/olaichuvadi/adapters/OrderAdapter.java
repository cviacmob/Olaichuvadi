package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.OrderInfo;

import java.util.List;

public class OrderAdapter extends BaseAdapter {

    private Context mContext;
    private List<OrderInfo> orditm;

    public OrderAdapter(Context mContext, List<OrderInfo> orditm) {
        this.mContext = mContext;
        this.orditm = orditm;
    }

    @Override
    public int getCount() {
        return orditm.size();
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
        public TextView tv1, tv2, tv3, tv4, tv5;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View ord = view;
        OrderAdapter.ViewHolder holder;
        OrderInfo oinfo = orditm.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ord = inflater.inflate(R.layout.orderitem, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) ord.findViewById(R.id.ordimg);
            holder.tv1 = (TextView) ord.findViewById(R.id.ordrtit);
            holder.tv2 = (TextView) ord.findViewById(R.id.ordrsta);
            holder.tv3 = (TextView) ord.findViewById(R.id.ordrdat);
            holder.tv4 = (TextView) ord.findViewById(R.id.ordrhd);
            holder.tv5 = (TextView) ord.findViewById(R.id.ordrno);
            ord.setTag(holder);
        } else {
            holder = (ViewHolder) ord.getTag();
        }
        holder.iv.setImageResource(oinfo.getOdrimg());
        holder.tv1.setText(oinfo.getOrdtit());
        holder.tv2.setText(oinfo.getDevsta());
        holder.tv3.setText(oinfo.getDevdat());
        return ord;
    }
}