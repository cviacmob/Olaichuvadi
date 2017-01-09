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
import com.cviac.olaichuvadi.datamodels.PurchasedInfo;

import java.util.List;

import static com.cviac.olaichuvadi.R.id.wish;

public class PurchasedAdapter extends BaseAdapter {

    private Context mContext;
    private List<PurchasedInfo> lib_pur;

    public PurchasedAdapter(Context mContext, List<PurchasedInfo> lib_pur) {
        this.mContext = mContext;
        this.lib_pur = lib_pur;
    }

    @Override
    public int getCount() {
        return lib_pur.size();
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
        public TextView tv1, tv2;
        public ImageButton wish, fav;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View purch = view;
        ViewHolder holder;
        PurchasedInfo puinfo = lib_pur.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            purch = inflater.inflate(R.layout.purchaseditem, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) purch.findViewById(R.id.lib_pur_img);
            holder.tv1 = (TextView) purch.findViewById(R.id.purchtit);
            holder.tv2 = (TextView) purch.findViewById(R.id.purchauth);
            holder.wish = (ImageButton) purch.findViewById(wish);
            holder.fav = (ImageButton) purch.findViewById(R.id.fav);
            purch.setTag(holder);
        } else {

            holder = (ViewHolder) purch.getTag();
        }
        holder.iv.setImageResource(puinfo.getBook_img());
        holder.tv1.setText(puinfo.getTit());
        holder.tv2.setText(puinfo.getAuth());
        return purch;
    }
}
