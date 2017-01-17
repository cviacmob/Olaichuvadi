package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<Category> list;

    public CategoryAdapter(Context mContext, List<Category> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        public TextView tv;
        public ImageView iv, click;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View shr = view;
        CategoryAdapter.ViewHolder holder;
        Category cinfo = list.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.categoryadapter, null);
            holder = new CategoryAdapter.ViewHolder();
            holder.iv = (ImageView) shr.findViewById(R.id.catimg);
            holder.tv = (TextView) shr.findViewById(R.id.cattxt);
            holder.click = (ImageView) shr.findViewById(R.id.prcd_img);
            shr.setTag(holder);
        } else {
            holder = (CategoryAdapter.ViewHolder) shr.getTag();
        }
        String url = "http://nheart.cviac.com//image//cache//catalog//ring22-500x500.jpg";
        Picasso.with(shr.getContext()).load(url).resize(50, 50).into(holder.iv);
        holder.tv.setText(cinfo.getName());
        return shr;
    }
}
