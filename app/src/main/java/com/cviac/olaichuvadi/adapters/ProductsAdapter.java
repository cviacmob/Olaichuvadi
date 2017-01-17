package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends BaseAdapter {

    private Context mContext;
    private List<Product> rowListItem;

    public ProductsAdapter(Context mContext, List<Product> rowListItem) {
        this.mContext = mContext;
        this.rowListItem = rowListItem;
    }

    @Override
    public int getCount() {
        return rowListItem.size();
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
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View ord = view;
        ProductsAdapter.ViewHolder holder;
        Product oinfo = rowListItem.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ord = inflater.inflate(R.layout.card_view_list, null);
            holder = new ProductsAdapter.ViewHolder();
            holder.iv = (ImageView) ord.findViewById(R.id.prd_img);
            holder.tv1 = (TextView) ord.findViewById(R.id.pname);
            holder.tv2 = (TextView) ord.findViewById(R.id.newprice);
            holder.tv3 = (TextView) ord.findViewById(R.id.oldprice);
            holder.tv3.setPaintFlags(holder.tv3.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            ord.setTag(holder);
        } else {
            holder = (ProductsAdapter.ViewHolder) ord.getTag();
        }
        holder.tv1.setText(oinfo.getName());
        holder.tv2.setText(oinfo.getPrice());
        holder.tv3.setText(oinfo.getSpecial());
        String url = oinfo.getThumb();
        url = url.replace("localhost", "192.168.1.16");
        try {
            Picasso.with(ord.getContext()).load(url).resize(500, 500).into(holder.iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ord;
    }
}