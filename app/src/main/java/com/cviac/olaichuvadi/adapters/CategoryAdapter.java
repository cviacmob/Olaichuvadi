package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<Category> list;

    public CategoryAdapter(Context mContext, List<Category> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        List<Category> childs = list.get(groupPosition).getCategories();
        if (childs != null) {
            return childs.size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<Category> childs = list.get(groupPosition).getCategories();
        if (childs != null) {
            return childs.get(childPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public static class ViewHolder {
        public TextView tv;
        public ImageView iv;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View shr = convertView;
        CategoryAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.categoryadapter, null);
            holder = new CategoryAdapter.ViewHolder();
            holder.iv = (ImageView) shr.findViewById(R.id.catimg);
            holder.tv = (TextView) shr.findViewById(R.id.cattxt);
//            holder.click = (ImageView) shr.findViewById(R.id.prcd_img);
            shr.setTag(holder);
        } else {
            holder = (CategoryAdapter.ViewHolder) shr.getTag();
        }
        Category cinfo = list.get(groupPosition);
        holder.tv.setText(cinfo.getName());
        String url = cinfo.getImage();
        if (url != null) {
            Picasso.with(shr.getContext()).load(url).resize(50, 50).into(holder.iv);
        }
        return shr;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View shr = convertView;
        CategoryAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.categoryadapter, null);
            holder = new CategoryAdapter.ViewHolder();
            holder.iv = (ImageView) shr.findViewById(R.id.catimg);
            holder.tv = (TextView) shr.findViewById(R.id.cattxt);
//            holder.click = (ImageView) shr.findViewById(R.id.prcd_img);
            shr.setTag(holder);
        } else {
            holder = (CategoryAdapter.ViewHolder) shr.getTag();
        }
        List<Category> childs = list.get(groupPosition).getCategories();
        Category cinfo = childs.get(childPosition);
        holder.tv.setText(cinfo.getName());
        String url = cinfo.getImage();
        if (url != null) {
            Picasso.with(shr.getContext()).load(url).resize(50, 50).into(holder.iv);
        }
        return shr;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}