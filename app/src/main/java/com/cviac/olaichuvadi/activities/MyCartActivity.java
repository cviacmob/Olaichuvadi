package com.cviac.olaichuvadi.activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.CartAdapter;
import com.cviac.olaichuvadi.datamodels.CartInfo;
import com.cviac.olaichuvadi.utilities.BadgeDrawable;

import java.util.ArrayList;
import java.util.List;

public class MyCartActivity extends AppCompatActivity {

    ListView lv;
    List<CartInfo> cartitms;
    Button proc;
    private LayerDrawable mCartMenuIcon;
    private int mCartCount=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        setTitle("My Cart");
        loadcrtitms();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CartAdapter adapter = new CartAdapter(MyCartActivity.this, cartitms);
        proc = (Button) findViewById(R.id.paybtn);
        lv = (ListView) findViewById(R.id.cartlist);
        lv.setAdapter(adapter);
        proc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Will be Proceeded to Pre-Payment", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void loadcrtitms() {
        cartitms = new ArrayList<>();

        CartInfo c1 = new CartInfo(R.mipmap.ic, "Title 1", "Desc 1", "$ 50");
        cartitms.add(c1);
        CartInfo c2 = new CartInfo(R.mipmap.ic, "Title 2", "Desc 2", "$ 65");
        cartitms.add(c2);
        CartInfo c3 = new CartInfo(R.mipmap.ic, "Title 3", "Desc 3", "$ 70");
        cartitms.add(c3);
        CartInfo c4 = new CartInfo(R.mipmap.ic, "Title 4", "Desc 4", "$ 60");
        cartitms.add(c4);
        CartInfo c5 = new CartInfo(R.mipmap.ic, "Title 5", "Desc 5", "$ 55");
        cartitms.add(c5);
        CartInfo c6 = new CartInfo(R.mipmap.ic, "Title 6", "Desc 6", "$ 45");
        cartitms.add(c6);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        mCartCount=cartitms.size();
        getMenuInflater().inflate(R.menu.cart, menu);
        mCartMenuIcon = (LayerDrawable) menu.findItem(R.id.cart).getIcon();
        setBadgeCount(this, mCartMenuIcon, String.valueOf(mCartCount++));
        return true;
    }
    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_cart_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_cart_badge, badge);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        onBackPressed();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.cart) {
        }
        return super.onOptionsItemSelected(item);
    }
}
