package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.ReviewedInfo;
import com.cviac.olaichuvadi.datamodels.ViewReviewInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewedAdapter extends BaseAdapter {

    private Context mContext;
    private List<ViewReviewInfo> lib_rev;

    public ReviewedAdapter(Context mContext, List<ViewReviewInfo> lib_rev) {
        this.mContext = mContext;
        this.lib_rev = lib_rev;
    }

    @Override
    public int getCount() {
        return lib_rev.size();
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
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View revv = view;
        ReviewedAdapter.ViewHolder holder;
        ViewReviewInfo puinfo = lib_rev.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            revv = inflater.inflate(R.layout.revieweditem, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) revv.findViewById(R.id.rev_img);
            holder.tv1 = (TextView) revv.findViewById(R.id.rev_tt);
            revv.setTag(holder);
        } else {
            holder = (ReviewedAdapter.ViewHolder) revv.getTag();
        }
        holder.tv1.setText(puinfo.getProduct_name());
        String url1 = mContext.getString(R.string.img_club);
        String url2 = puinfo.getImage();
        StringBuilder url3 = new StringBuilder();
        url3.append(url1 + url2);
        String url = url3.toString();
        if (!url.isEmpty()) {
            Picasso.with(revv.getContext()).load(url).placeholder(R.mipmap.log).resize(500, 500).into(holder.iv);
        }
        return revv;
    }
}