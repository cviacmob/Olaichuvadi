package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.GetPostInfo;

import java.util.List;

public class MessagesAdapter extends BaseAdapter {

    Context mContext;
    List<GetPostInfo> posts;

    public MessagesAdapter(Context mContext, List<GetPostInfo> posts) {
        this.mContext = mContext;
        this.posts = posts;
    }

    public MessagesAdapter(List<GetPostInfo> posts, Context mContext) {
        this.posts = posts;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return posts.size();
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
        public TextView tv1, tv2, tv3, tv4;
        public ImageView iv1, iv2, iv3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View msg = convertView;
        ViewHolder holder;
        GetPostInfo postinfo = posts.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            msg = inflater.inflate(R.layout.activity_messages_adapter, null);
            holder = new ViewHolder();
            holder.tv1 = (TextView) msg.findViewById(R.id.username);
            holder.tv2 = (TextView) msg.findViewById(R.id.post_club_name);
            holder.tv3 = (TextView) msg.findViewById(R.id.message);
            holder.tv4 = (TextView) msg.findViewById(R.id.count);
            holder.iv1 = (ImageView) msg.findViewById(R.id.user);
            holder.iv2 = (ImageView) msg.findViewById(R.id.sharedimg);
            holder.iv3 = (ImageView) msg.findViewById(R.id.likpost);
            msg.setTag(holder);
        } else {
            holder = (ViewHolder) msg.getTag();
        }
        return null;
    }
}