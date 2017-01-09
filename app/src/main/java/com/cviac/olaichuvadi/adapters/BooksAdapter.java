package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        public ImageView iv;
        public TextView tv1, tv2;
        public ImageView edt;
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
            holder.iv = (ImageView) shr.findViewById(R.id.book_image);
            holder.tv1 = (TextView) shr.findViewById(R.id.lib_book_tit);
            holder.tv2 = (TextView) shr.findViewById(R.id.lib_book_auhtor);
            holder.edt = (ImageView) shr.findViewById(R.id.editbton);
            shr.setTag(holder);
        } else {
            holder = (BooksAdapter.ViewHolder) shr.getTag();
        }
        holder.iv.setImageResource(cinfo.getBooks());
        holder.tv1.setText(cinfo.getBook_tit());
        holder.tv2.setText(cinfo.getBook_author());
        holder.edt.setImageResource(cinfo.getEdt_img());
        return shr;
    }
}
