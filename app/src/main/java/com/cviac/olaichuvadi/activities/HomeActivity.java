package com.cviac.olaichuvadi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cviac.olaichuvadi.R;
import com.cviac.olaichuvadi.utilities.Prefs;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setUserdetails(navigationView);
    }

    private void setUserdetails(NavigationView navigationView) {
        View hview = navigationView.getHeaderView(0);
        TextView un = (TextView) hview.findViewById(R.id.nav_name);
        TextView um = (TextView) hview.findViewById(R.id.nav_mail);

        String name = Prefs.getString("Name", "");
        String phne = Prefs.getString("Mail", "");

        un.setText(name);
        um.setText(phne);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.cart) {
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_library) {

            Intent lib = new Intent(HomeActivity.this, MyLibraryActivity.class);
            startActivity(lib);

        } else if (id == R.id.nav_community) {

            Intent com = new Intent(HomeActivity.this, MyCommunityActivity.class);
            startActivity(com);

        } else if (id == R.id.nav_cart) {

            Intent cart = new Intent(HomeActivity.this, MyCartActivity.class);
            startActivity(cart);

        } else if (id == R.id.nav_account) {

            Intent acc = new Intent(HomeActivity.this, MyAccountActivity.class);
            startActivity(acc);


        } else if (id == R.id.nav_orders) {

            Intent order = new Intent(HomeActivity.this, MyOrdersActivity.class);
            startActivity(order);

        } else if (id == R.id.nav_logout) {

            logout();
            Intent lgot = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(lgot);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        Prefs.edit();
        Prefs.remove("isregistered");
    }
}
