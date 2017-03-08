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

public class MyComm_Authors extends AppCompatActivity {

    GridView gv;
    private List<AuthorsInfo> authr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comm__authors);

        loadauthors();

        setTitle(R.string.tab_authors);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gv = (GridView) findViewById(R.id.authgrd);
        AuhtorsAdapter adapter = new AuhtorsAdapter(this, authr);
        gv.setAdapter(adapter);
    }

    private void loadauthors() {

        authr = new ArrayList<>();

        AuthorsInfo ai1 = new AuthorsInfo("VAIRAMUTHU");
        authr.add(ai1);

        AuthorsInfo ai2 = new AuthorsInfo("SUJATHA");
        authr.add(ai2);

        AuthorsInfo ai3 = new AuthorsInfo("VAALI");
        authr.add(ai3);

        AuthorsInfo ai4 = new AuthorsInfo("KALKI");
        authr.add(ai4);

        AuthorsInfo ai5 = new AuthorsInfo("JAYA KANTHAN");
        authr.add(ai5);

        AuthorsInfo ai6 = new AuthorsInfo("VAIRAMUTHU");
        authr.add(ai6);

        AuthorsInfo ai7 = new AuthorsInfo("SUJATHA");
        authr.add(ai7);

        AuthorsInfo ai8 = new AuthorsInfo("VAALI");
        authr.add(ai8);

        AuthorsInfo ai9 = new AuthorsInfo("KALKI");
        authr.add(ai9);

        AuthorsInfo ai10 = new AuthorsInfo("JAYA KANTHAN");
        authr.add(ai10);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}