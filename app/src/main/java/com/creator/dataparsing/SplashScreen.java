package com.creator.dataparsing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.creator.dataparsing.fragment.HolderFragmentHome;
import com.creator.dataparsing.preference.Spmanger;
import com.creator.dataparsing.registration.login;

/**
 * Created by admin on 24/08/16.
 */
public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
//        getSupportActionBar().hide();
        //  scheduleNotify();

/*TODO:the splash screen will take 45sec to launch it so that all the network call will happen */


//            startService(new Intent(getBaseContext(), ScheduledService.class));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i;
                    if (Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).getUser() != null)
                        i = new Intent(SplashScreen.this, HolderFragmentHome.class);
                    else
                        i = new Intent(SplashScreen.this, login.class);
                    startActivity(i);
                    finish();
//                    final Intent mainIntent = new Intent(SplashScreen.this, login.class);
//                    SplashScreen.this.startActivity(mainIntent);
//                    SplashScreen.this.finish();
                }
            }, 10000);






    }
}
