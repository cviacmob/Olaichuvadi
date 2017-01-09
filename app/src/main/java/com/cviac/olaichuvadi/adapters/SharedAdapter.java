package com.cviac.olaichuvadi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.datamodels.SharedInfo;

import java.util.List;

public class SharedAdapter extends BaseAdapter{

    private Context mContext;
    private List<SharedInfo> shrd;

    public SharedAdapter(Context mContext, List<SharedInfo> shrd) {
        this.mContext = mContext;
        this.shrd = shrd;
    }

    @Override
    public int getCount() {
        return shrd.size();
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
        public TextView tv1,tv2,tv3;
        public Button clk;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View shr = view;
        SharedAdapter.ViewHolder holder;
        SharedInfo cinfo=shrd.get(i);
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            shr=inflater.inflate(R.layout.shared_item,null);
            holder = new ViewHolder();
            holder.iv=(ImageView) shr.findViewById(R.id.book_img);
            holder.tv1=(TextView) shr.findViewById(R.id.book_name);
            holder.tv2=(TextView) shr.findViewById(R.id.u_name);
            holder.tv3=(TextView) shr.findViewById(R.id.price);
            holder.clk=(Button) shr.findViewById(R.id.clkbtn);
            shr.setTag(holder);
        } else{
            holder = (SharedAdapter.ViewHolder) shr.getTag();
        }
        holder.iv.setImageResource(cinfo.getBook_img());
        holder.tv1.setText(cinfo.getBook_tit());
        holder.tv2.setText(cinfo.getUname());
        holder.tv3.setText(cinfo.getPrice());
        return shr;
    }
}
