package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.BooksInfo;

import java.util.List;

public class BooksAdapter extends BaseAdapter {
    private Context mContext;
    private List<BooksInfo> book;

    public BooksAdapter(Context mContext, List<BooksInfo> book) {
        this.mContext = mContext;
        this.book = book;
    }

    @Override
    public int getCount() {
        return book.size();
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
        public TextView tv1;
        public ImageView iv1, iv2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View shr = view;
        BooksAdapter.ViewHolder holder;
        BooksInfo cinfo = book.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.book_item, null);
            holder = new BooksAdapter.ViewHolder();
            holder.tv1 = (TextView) shr.findViewById(R.id.lib_book_tit);
            holder.iv1 = (ImageView) shr.findViewById(R.id.revbtn);
            holder.iv2 = (ImageView) shr.findViewById(R.id.favbtn);
            shr.setTag(holder);
        } else {
            holder = (BooksAdapter.ViewHolder) shr.getTag();
        }
        holder.tv1.setText(cinfo.getTit());
        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Review Clicked", Toast.LENGTH_LONG).show();
            }
        });
        holder.iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Favourites Clicked", Toast.LENGTH_LONG).show();
            }
        });
        return shr;
    }
}