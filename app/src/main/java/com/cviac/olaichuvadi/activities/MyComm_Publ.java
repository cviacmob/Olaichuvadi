package com.cviac.olaichuvadi.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.adapters.AuhtorsAdapter;
import com.cviac.olaichuvadi.datamodels.AuthorsInfo;

import java.util.ArrayList;
import java.util.List;

public class MyComm_Publ extends AppCompatActivity {

    GridView gv;
    private List<AuthorsInfo> publ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comm__publ);

        loadpub();

        setTitle(R.string.tab_pub);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gv = (GridView) findViewById(R.id.pubgrd);
        AuhtorsAdapter adapter = new AuhtorsAdapter(this, publ);
        gv.setAdapter(adapter);
    }

    private void loadpub() {

        publ = new ArrayList<>();

        AuthorsInfo ai1 = new AuthorsInfo("PEE VEE PUBLICATIONS");
        publ.add(ai1);

        AuthorsInfo ai2 = new AuthorsInfo("JK PUBLISHERS");
        publ.add(ai2);

        AuthorsInfo ai3 = new AuthorsInfo("SANDHYA PUBLICATIONS");
        publ.add(ai3);

        AuthorsInfo ai4 = new AuthorsInfo("VIKATAN PUBLICATIONS");
        publ.add(ai4);

        AuthorsInfo ai5 = new AuthorsInfo("A.R.K PUBLICATIONS");
        publ.add(ai5);

        AuthorsInfo ai6 = new AuthorsInfo("PEE VEE PUBLICATIONS");
        publ.add(ai6);

        AuthorsInfo ai7 = new AuthorsInfo("JK PUBLISHERS");
        publ.add(ai7);

        AuthorsInfo ai8 = new AuthorsInfo("SANDHYA PUBLICATIONS");
        publ.add(ai8);

        AuthorsInfo ai9 = new AuthorsInfo("VIKATAN PUBLICATIONS");
        publ.add(ai9);

        AuthorsInfo ai10 = new AuthorsInfo("A.R.K PUBLICATIONS");
        publ.add(ai10);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
