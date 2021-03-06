package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.ViewAddedBooksInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BooksAdapter extends BaseAdapter {
    private Context mContext;
    private List<ViewAddedBooksInfo> book;

    public BooksAdapter(Context mContext, List<ViewAddedBooksInfo> book) {
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
        public ImageView iv;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View shr = view;
        BooksAdapter.ViewHolder holder;
        ViewAddedBooksInfo cinfo = book.get(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr = inflater.inflate(R.layout.book_item, null);
            holder = new BooksAdapter.ViewHolder();
            holder.tv1 = (TextView) shr.findViewById(R.id.lib_book_tit);
            holder.iv = (ImageView) shr.findViewById(R.id.book_image);
            shr.setTag(holder);
        } else {
            holder = (BooksAdapter.ViewHolder) shr.getTag();
        }
        holder.tv1.setText(cinfo.getTitle());
        String url1 = mContext.getString(R.string.img_club);
        String url2 = cinfo.getImage();
        StringBuilder url3 = new StringBuilder();
        url3.append(url1 + url2);
        String url = url3.toString();
        if (!url.isEmpty()) {
            Picasso.with(shr.getContext()).load(url).placeholder(R.mipmap.log).resize(500, 500).into(holder.iv);
        }
        return shr;
    }
}