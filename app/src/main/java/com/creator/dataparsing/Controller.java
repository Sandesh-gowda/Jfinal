package com.creator.dataparsing;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.creator.dataparsing.preference.Spmanger;

/**
 * Created by admin on 30/06/16.
 */
public class Controller extends Application {


    private static Controller mInstance;
private static Spmanger manager;
    private RequestQueue mRequestQueue;

    public static final String TAG = Controller.class
            .getSimpleName();



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized Controller getInstance() {
        return mInstance;
    }

    public Spmanger getPrefManager(String pref_key_value) {
        if (manager == null) {
            manager = new Spmanger(this, pref_key_value);
        }
        return manager;


    }

    /*  sumeeth code goes here  */


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}
