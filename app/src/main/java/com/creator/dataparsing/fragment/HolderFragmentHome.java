package com.creator.dataparsing.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;

import com.creator.dataparsing.Controller;
import com.creator.dataparsing.HomeSearch;
import com.creator.dataparsing.ListOfJobApplied;
import com.creator.dataparsing.R;
import com.creator.dataparsing.aboutus;
import com.creator.dataparsing.model.JobUserData;
import com.creator.dataparsing.preference.Spmanger;
import com.creator.dataparsing.registration.Registration_two;
import com.creator.dataparsing.registration.login;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 25/08/16.
 */
public class HolderFragmentHome extends AppCompatActivity {



    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;


    private Toolbar toolbar;
    private TabLayout tabLayout;

    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.home,
            R.drawable.ic_fix,
            R.drawable.inbox
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holder_fragment_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     //   toolbar = (Toolbar) findViewById(R.id.toolbar);
   //   setSupportActionBar(toolbar);

    //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      //  getSupportActionBar().hide();
    //  getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//          result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
      JobUserData user = Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).getUser();
        String mName;
        String mEmail;
        if(user.getName() != null){
           mName =   user.getName();
           mEmail = user.getEmail();

        }else {

            mEmail= "jaldijob@gmail.com";
            mName="Jaldijob";

        }
        final IProfile profile = new ProfileDrawerItem().withName(mName).withEmail(mEmail).withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460").withIdentifier(100);

        //header
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)

                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile).build();

        //drawer
        result = new DrawerBuilder()
               .withActivity(this)
                .withToolbar(toolbar)
.withAccountHeader(headerResult)
                .withHasStableIds(true)
               //set the AccountHeader we created earlier for the header
              .addDrawerItems(  new PrimaryDrawerItem().withName("Home").withIcon(GoogleMaterial.Icon.gmd_access_time).withIdentifier(1).withSelectable(false),
                      new PrimaryDrawerItem().withName("Search Jobs").withIcon(GoogleMaterial.Icon.gmd_search).withIdentifier(5).withSelectable(false),
                      new PrimaryDrawerItem().withName("Applied Job").withIcon(FontAwesome.Icon.faw_bell).withIdentifier(2).withSelectable(false),
                      new ExpandableDrawerItem().withName("Setting").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(19).withSelectable(false).withSubItems(
                              new SecondaryDrawerItem().withName("Edit Profile").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_edit).withIdentifier(9),
                              new SecondaryDrawerItem().withName("Logout").withLevel(3).withIcon(GoogleMaterial.Icon.gmd_developer_mode).withIdentifier(4)

                      ),
                      new PrimaryDrawerItem().withName("Share App").withIcon(FontAwesome.Icon.faw_share).withIdentifier(6).withSelectable(false),
                      new PrimaryDrawerItem().withName("About us").withIcon(FontAwesome.Icon.faw_question_circle).withIdentifier(3).withSelectable(false)





                      ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                              //  intent = new Intent(HolderFragmentHome.this, JobApplied.class);


                            }
                            else if(drawerItem.getIdentifier() == 2){
                               // intent = new Intent(HolderFragmentHome.this, SplashScreen.class);
                                intent = new Intent(HolderFragmentHome.this, ListOfJobApplied.class);
                                //some more else if ;
                            }else if(drawerItem.getIdentifier() == 3){
                                intent = new Intent(HolderFragmentHome.this,aboutus.class);
                              //  intent = new Intent(HolderFragmentHome.this, SplashScreen.class);

                            }else if (drawerItem.getIdentifier() == 4){
                                doLogout();
                               // intent = new Intent(HolderFragmentHome.this, SplashScreen.class);

                            }else if (drawerItem.getIdentifier() == 5){
                                 intent = new Intent(HolderFragmentHome.this, HomeSearch.class);
                            }else if (drawerItem.getIdentifier() == 9){
                                intent = new Intent(HolderFragmentHome.this, Registration_two.class);
                            }
                            else if (drawerItem.getIdentifier()==6){
                                intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("text/html");

                                        //share
                                //
                                // Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                               // sharingIntent.setType("text/html");
                                intent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p><a>https://play.google.com/store/apps/details?id=com.creator.dataparsing</a></p>"));
                                startActivity(Intent.createChooser(intent,"Share using"));
                                // <code></code>


                            }
                            if (intent != null) {
                                HolderFragmentHome.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .build();

    }





    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Home(), "Home");
        adapter.addFragment(new AcceptNotification(), "Inbox");
        adapter.addFragment(new JobNotifcation(), "Notify");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }



    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        //add the values which need to be saved from the drawer to the bundle
//        outState = result.saveInstanceState(outState);
//        //add the values which need to be saved from the accountHeader to the bundle
//
//        super.onSaveInstanceState(outState);
//    }



    private void doLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HolderFragmentHome.this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to Logout?");
        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).logoutUser();
                startActivity(new Intent(HolderFragmentHome.this, login.class));
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog ad = builder.create();
        ad.show();
    }
    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}

