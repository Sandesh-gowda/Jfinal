package com.creator.dataparsing;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.creator.dataparsing.fragment.HolderFragmentHome;
import com.creator.dataparsing.fragment.Home;

/**
 * Created by admin on 25/08/16.
 */
public class HomeNavigatation extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_navigation);

        initViews();
        setUpHeaderView();
        onMenuItemSelected();

        //At start set home fragment
        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.home);
            MenuItem item = navigationView.getMenu().findItem(R.id.home);
            Fragment homeFragment = fragmentManager.findFragmentByTag(item.getTitle().toString());
            if (homeFragment == null)
                fragmentManager.beginTransaction().replace(R.id.frame_container, new Home(), item.getTitle().toString()).commit();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.slider_menu);
        fragmentManager = getSupportFragmentManager();
        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, toolbar, // nav menu toggle icon
                R.string.drawer_open, // nav drawer open - description for
                // accessibility
                R.string.drawer_close // nav drawer close - description for
                // accessibility
        ) {
            public void onDrawerClosed(View view) {
            }

            public void onDrawerOpened(View drawerView) {

            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setUpHeaderView() {
        View headerView = navigationView.inflateHeaderView(R.layout.header);
//        TextView textOne = (TextView) headerView.findViewById(R.id.username);
//        TextView textTwo = (TextView) headerView.findViewById(R.id.email_address);
    }


    /*  Method for Navigation View item selection  */
    private void onMenuItemSelected() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                //Check and un-check menu item if they are checkable behaviour
                if (item.isCheckable()) {
                    if (item.isChecked()) item.setChecked(false);
                    else item.setChecked(true);
                }

                //Closing drawer on item click
                mDrawerLayout.closeDrawers();

                switch (item.getItemId()) {

                    case R.id.home:
                        break;
                    case R.id.dashboard:
                         break;


                    case R.id.my_profile:
                        //Replace fragment

                        break;

                    case R.id.Contact_us:

                        break;


                }

                return false;
            }
        });
    }
    private void replaceDashboardFragment(MenuItem item) {
        Fragment dashboardFragment = fragmentManager.findFragmentByTag(item.getTitle().toString());
        if (dashboardFragment == null)
            fragmentManager.beginTransaction().replace(R.id.frame_container, new Home(), item.getTitle().toString()).commit();
    }

    //On backs press check if drawer is open and closed
    @Override
    public void onBackPressed() {
        MenuItem item = navigationView.getMenu().findItem(R.id.home);

        Fragment dashBoardFragment = fragmentManager.findFragmentByTag(item.getTitle().toString());
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT))
            mDrawerLayout.closeDrawers();
        else if (dashBoardFragment == null) {
            navigationView.setCheckedItem(R.id.home);
            replaceDashboardFragment(item);
        } else
            super.onBackPressed();
    }




    }
