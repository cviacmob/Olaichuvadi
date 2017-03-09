package com.cviac.olaichuvadi.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.ProductCartInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PaymentAdapter extends BaseAdapter {

    private Context mContext;
    private List<ProductCartInfo> cartProducts;
    ProgressDialog progressDialog = null;

    public PaymentAdapter(Context mContext, List<ProductCartInfo> cartProducts) {
        this.mContext = mContext;
        this.cartProducts = cartProducts;
    }

    @Override
    public int getCount() {
        return cartProducts.size();
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
        public TextView tv1, tv2, tv3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View crt = convertView;
        PaymentAdapter.ViewHolder holder;
        final ProductCartInfo cinfo = cartProducts.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            crt = inflater.inflate(R.layout.payment_adapter, null);
            holder = new PaymentAdapter.ViewHolder();
            holder.iv = (ImageView) crt.findViewById(R.id.orderimg);
            holder.tv1 = (TextView) crt.findViewById(R.id.prd_tit);
            holder.tv2 = (TextView) crt.findViewById(R.id.txtprice);
            holder.tv3 = (TextView) crt.findViewById(R.id.qty);
            crt.setTag(holder);
        } else {
            holder = (PaymentAdapter.ViewHolder) crt.getTag();
        }
        String prd_img = cinfo.getImage();
        Picasso.with(mContext).load(prd_img).placeholder(R.mipmap.bookgrd).resize(60, 60).into(holder.iv);
        holder.tv1.setText(cinfo.getName());
        holder.tv2.setText(cinfo.getTotal());
        holder.tv3.setText(cinfo.getQuantity());
        return crt;
    }
}