package com.cviac.olaichuvadi.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.activities.Product_Details;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView bookName;
    public ImageView bookPhoto;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        bookName = (TextView) itemView.findViewById(R.id.country_name);
        bookPhoto = (ImageView) itemView.findViewById(R.id.country_photo);
    }

    @Override
    public void onClick(View view) {
        Intent itm = new Intent(view.getContext(), Product_Details.class);
        view.getContext().startActivity(itm);
    }
}
