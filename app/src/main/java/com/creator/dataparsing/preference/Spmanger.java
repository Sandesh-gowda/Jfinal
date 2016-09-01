package com.creator.dataparsing.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.creator.dataparsing.model.JobUserData;

/**
 * Created by admin on 26/08/16.
 */
public class Spmanger {

    public static final String Login_Preferences = "Login_Preferences";
    public static final String User_Id = "User_Id";
    public static final String User_Name = "User_Name";
    public static final String User_EmailId = "User_EmailId";
    public static final String User_Mobile_Number = "User_Mobile_Number";
    public static final String User_City = "User_City";
    public static final String User_State = "User_State";
    public static final String User_Pin_code = "User_Pin_code";
    public static final String User_CreatedAt = "User_CreatedAt";
    public static final String Dealer_Id = "Dealer_Id";

    private String TAG = Spmanger.class.getSimpleName();

    // Shared Preferences
    private SharedPreferences pref;

    // Editor for Shared preferences
    private SharedPreferences.Editor editor;

    // Context
    private Context _context;

    // Shared pref mode
    private int PRIVATE_MODE = 0;


    // Constructor
    public Spmanger(Context context, String pref_key_value) {
        this._context = context;
        pref = _context.getSharedPreferences(pref_key_value, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void storeRegisterUser(JobUserData user) {
        editor.putString(User_Id, user.getId());
        editor.putString(User_Name, user.getName());
       editor.putString(User_EmailId, user.getEmail());
      //  editor.putString(User_Mobile_Number, user.getUser_mobile());
      //  editor.putString(User_City, user.getUser_city());
      //  editor.putString(User_State, user.getUser_state());
      //  editor.putString(Dealer_Id, user.getDealer_id());
      //  editor.putString(User_CreatedAt, user.getCreated_at());
      //  editor.putString(User_Pin_code, user.getUser_pin_code());

        editor.commit();

        Log.v(TAG, "UserID is stored in shared preferences. " + user.getId());
    }



    public JobUserData getUser() {
        if (pref.getString(User_Id, null) != null) {

            String user_id = pref.getString(User_Id, null);

           String user_name = pref.getString(User_Name, null);
           String user_email = pref.getString(User_EmailId, null);
       //     String user_mobile = pref.getString(Constants.User_Mobile_Number, null);
       //     String city = pref.getString(Constants.User_City, null);
        //    String state = pref.getString(Constants.User_State, null);
          //  String dealer_id = pref.getString(Constants.Dealer_Id, null);
        //    String created_at = pref.getString(Constants.User_CreatedAt, null);
         //   String pin_code = pref.getString(Constants.User_Pin_code, null);
            Log.v(TAG, "Get User id " + user_id + ", it came move to further");
            return new JobUserData(user_id,user_name ,user_email );
        }
        return null;
    }


    public void logoutUser() {
        editor.clear();
        editor.commit();
    }
}
