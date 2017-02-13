package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.ProductCartInfo;

import java.util.List;

public class CartItemAdapter extends BaseAdapter {

    private Context mContext;
    private static int counter = 0;
    private String stringVal;
    private List<ProductCartInfo> cartProducts;

    public CartItemAdapter(Context mContext, List<ProductCartInfo> cartProducts) {
        this.mContext = mContext;
        this.cartProducts = cartProducts;
    }

    @Override
    public int getCount() {
        return cartProducts.size();
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
        public TextView tv1, tv2, tv3, tv4;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View crt = view;
        ViewHolder holder;
        ProductCartInfo cinfo = cartProducts.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            crt = inflater.inflate(R.layout.cartitems, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) crt.findViewById(R.id.crtimg);
            holder.tv1 = (TextView) crt.findViewById(R.id.crttit);
            holder.tv4 = (TextView) crt.findViewById(R.id.crtprc);
            final TextView tv2 = (TextView) crt.findViewById(R.id.numcounts);
            holder.tv2 = tv2;
            holder.tv3 = (TextView) crt.findViewById(R.id.crtprc);
            ImageButton txtplus = (ImageButton) crt.findViewById(R.id.addprd);
            txtplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("src", "Increasing value...");
                    counter++;
                    stringVal = Integer.toString(counter);
                    tv2.setText(stringVal);
                }
            });
            final ImageButton txtminus = (ImageButton) crt.findViewById(R.id.delprd);
            txtminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("src", "Decreasing value...");
                    counter--;
                    stringVal = Integer.toString(counter);
                    tv2.setText(stringVal);
                    if (counter <= 0) {
                        txtminus.setEnabled(false);
                        tv2.setText("0");
                    }
                }
            });
            crt.setTag(holder);
        } else {
            holder = (ViewHolder) crt.getTag();
        }
        holder.tv4.setText(cinfo.getTotal());
        holder.tv1.setText(cinfo.getName());
        holder.tv2.setText(cinfo.getQuantity());
        return crt;
    }
}
