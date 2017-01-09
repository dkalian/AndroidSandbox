package com.shandrakov.sandbox.presentation.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shandrakov.sandbox.R;
import com.shandrakov.sandbox.presentation.common.BaseActivity;

public class MainActivity extends BaseActivity
                       implements NavigationView.OnNavigationItemSelectedListener,
                                  MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainPresenting presenter = register(new MainPresenter(this));

        Toolbar toolbar = (Toolbar) findViewById(com.shandrakov.sandbox.R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(com.shandrakov.sandbox.R.id.fab);
        fab.setOnClickListener(view -> {presenter.onCreateNewUserButtonPressed();});

        DrawerLayout drawer = (DrawerLayout) findViewById(com.shandrakov.sandbox.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.shandrakov.sandbox.R.string.navigation_drawer_open, com.shandrakov.sandbox.R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.shandrakov.sandbox.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(com.shandrakov.sandbox.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.shandrakov.sandbox.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == com.shandrakov.sandbox.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == com.shandrakov.sandbox.R.id.nav_camera) {
            // Handle the camera action
        } else if (id == com.shandrakov.sandbox.R.id.nav_gallery) {

        } else if (id == com.shandrakov.sandbox.R.id.nav_slideshow) {

        } else if (id == com.shandrakov.sandbox.R.id.nav_manage) {

        } else if (id == com.shandrakov.sandbox.R.id.nav_share) {

        } else if (id == com.shandrakov.sandbox.R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(com.shandrakov.sandbox.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public Context context() {
        return this;
    }
}
