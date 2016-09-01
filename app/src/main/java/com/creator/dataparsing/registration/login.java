package com.creator.dataparsing.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.creator.dataparsing.Controller;
import com.creator.dataparsing.MainActivity;
import com.creator.dataparsing.R;
import com.creator.dataparsing.forgotpassword;
import com.creator.dataparsing.fragment.HolderFragmentHome;
import com.creator.dataparsing.model.JobUserData;
import com.creator.dataparsing.preference.Spmanger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 24/08/16.
 *
 * login
 * username
 * password
 */
public class login extends AppCompatActivity implements View.OnClickListener {

    EditText name, password;
    LinearLayout login, signup;
    TextView terms_text;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_jaldijob);
        initview();

    }


    public void initview() {
        name = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (LinearLayout) findViewById(R.id.login_btn);
        signup = (LinearLayout) findViewById(R.id.forgot_pass_btn);
   terms_text= (TextView)findViewById(R.id.terms_text) ;
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        terms_text.setOnClickListener(this);


    }

    public void validate() {
        String mName = name.getText().toString();
        String mPass = password.getText().toString();
        callNetwork(mName, mPass);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_btn:
                validate();



                break;
            case R.id.forgot_pass_btn:
                // openFileSelector(true);
                //callNetwork();
                //call image to pule from the file system
                //move to signup
                Intent intendone = new Intent(getApplicationContext(), Registration_one.class);
                startActivity(intendone);


                break;
            case R.id.terms_text:
                Intent in = new Intent(getApplicationContext(),forgotpassword.class);
                startActivity(in);
                finish();

        }
    }


    public void callNetwork(final String username, final String password) {

        StringRequest jsonObjReq = new StringRequest(Request.Method.POST,
                "http://jaldijob.in/test/test/v1/login",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("JUST TAG", response.toString());
                      //  pDialog.hide();

                        try{

                            JSONObject json = new JSONObject(response);
                          String uid =  json.getString("user_id");
                            String name = json.getString("name");
                            String email = json.getString("email");
                            Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).storeRegisterUser(new JobUserData(uid,name ,email ));

if(uid != null){
    Intent intendone = new Intent(getApplicationContext(), HolderFragmentHome.class);
    startActivity(intendone);


}else{
    Toast.makeText(getApplication(),"Sorry try again later",Toast.LENGTH_LONG).show();
}
        //   Toast.makeText(getApplication(),"uid"+uid +"name"+name+"email"+email+"",Toast.LENGTH_LONG).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("JUST", "Error: " + error.getMessage());
             //   pDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
             //   params.put("email", "abc@androidhive.info");
                params.put("password", password);

                return params;
            }

        };

        Controller.getInstance().getRequestQueue().add(jsonObjReq);

    }
}
