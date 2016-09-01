package com.creator.dataparsing.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.creator.dataparsing.Controller;
import com.creator.dataparsing.Job_Details;
import com.creator.dataparsing.R;
import com.creator.dataparsing.VolleyMultipartRequest;
import com.creator.dataparsing.model.JobUserData;
import com.creator.dataparsing.model.Locality_json;
import com.creator.dataparsing.preference.Spmanger;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by admin on 23/08/16.
 */

//profile details

public class Registration_one extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private String responseData;
    public String searchUrl ;
    private View view;
    LinearLayout submitbutton;
    TextInputLayout  tilName, tilEmail,tilPassword,tilMobile,tilCity,tilLocality,tilIndustry,tilExperience;
    EditText mName, mEmail, mPassword, mMobile, mCity;
    AutoCompleteTextView mExperiece,mLocality,mIndustry;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter_loc;
    ArrayAdapter<String> adapter_ind;
    String[] experince = {"Fresher","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
    public static ArrayList<String> location = new ArrayList<>();
    String [] industry = {"Internet Jobs","HR/Admin Jobs","Delivery Jobs","House Keeping Jobs","Restaurant Jobs","Security Guard Jobs","Legal Jobs","Marketing Jobs","Finance Jobs","Cashier Jobs","Data Entry Jobs","BPO Jobs","Engineering Jobs","Driver Jobs","Beautician Jobs","Tailor Jobs"};
    String getLocation,getExperince;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_jaldijob_one);
        initViews();

        try {
            locality();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        submitbutton=(LinearLayout) findViewById(R.id.submit_btn);
        submitbutton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                valiateData();


            }
        });

    }

    private void valiateData() {
        String nName = mName.getText().toString();
        String nEmail = mEmail.getText().toString();
        String nPasswd = mPassword.getText().toString();
        String nMobile = mMobile.getText().toString();
        String nCity = mCity.getText().toString();
        String nLocaliyt = mLocality.getText().toString();
        String nInsustr = mIndustry.getText().toString();
        String nExperience = mExperiece.getText().toString();



        Toast.makeText(getApplicationContext(),"hhhhaa ckikced " + nExperience +"",Toast.LENGTH_SHORT).show();
        register(nName,nEmail,nPasswd,nMobile,nCity,nLocaliyt,nInsustr,nExperience);
    }

    private void initViews() {


        tilName = (TextInputLayout)findViewById(R.id.input_layout_name);
        tilEmail    =(TextInputLayout)findViewById(R.id.input_layout_email);
        tilPassword    =(TextInputLayout)findViewById(R.id.input_layout_password);
        tilMobile    =(TextInputLayout)findViewById(R.id.input_layout_mobile);
        tilCity    =(TextInputLayout)findViewById(R.id.input_layout_city);
        tilLocality   =(TextInputLayout)findViewById(R.id.input_layout_fun_area);
        tilIndustry   =(TextInputLayout)findViewById(R.id.input_layout_industry);
        tilExperience   =(TextInputLayout)findViewById(R.id.input_layout_exp);

        mName = (EditText) findViewById(R.id.name);
        mEmail   = (EditText) findViewById(R.id.email);

        mPassword   = (EditText) findViewById(R.id.password);
        mMobile   = (EditText) findViewById(R.id.mobile_field);
        mCity  = (EditText) findViewById(R.id.city);

        mExperiece   = (AutoCompleteTextView) findViewById(R.id.total_exp);
        adapter = new ArrayAdapter<String>(Registration_one.this,android.R.layout.select_dialog_item,experince);
        mExperiece.setThreshold(1);
        mExperiece.setAdapter(adapter);
        mExperiece.setOnItemClickListener(this);

        mLocality   = (AutoCompleteTextView) findViewById(R.id.fun_area);
        adapter_loc = new ArrayAdapter<String>(Registration_one.this,android.R.layout.select_dialog_item,location);
        mLocality.setThreshold(1);
        mLocality.setAdapter(adapter_loc);
        mLocality.setOnItemClickListener(this);

        mIndustry   = (AutoCompleteTextView) findViewById(R.id.industry);
        adapter_ind = new ArrayAdapter<String>(Registration_one.this,android.R.layout.select_dialog_item,industry);
        mIndustry.setThreshold(1);
        mIndustry.setAdapter(adapter_ind);
        mIndustry.setOnItemClickListener(this);



    }



    public void locality() throws JSONException {


        try {


        JSONArray jsonArray =  new JSONArray(Locality_json.locality);

        for (int i = 0;i<=jsonArray.length();i++)
        {

            JSONObject object =jsonArray.getJSONObject(i);
            String locality_str  = object.getString("location");
            location.add(locality_str);

        }




    }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }



//    private void register(final String name ,final String email, final String passwd, final String mobile , final String city, final String locality, final String indus,
//
//                          final String exp){
//        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
//                "http://www.jaldijob.in/test/test/v1/contact",
//                new Response.Listener<String>() {
//
//
//                    @Override
//                    public void onResponse(String response) {
//                        responseData = response;
//                        setData(response);
//                        VolleyLog.d("volley", "Error: " + response);
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("volley", "Error: " + error.getMessage());
//                error.printStackTrace();
//                Log.d("MenuFragment", "onErrorResponse : 103");
//            }
//        }){
//
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("username", name);
//                params.put("email",email);
//                params.put("password",passwd);
//                params.put("mobile",mobile);
//                params.put("city",city);
//                params.put("locality",locality);
//                params.put("industry",indus);
//                params.put("experence",exp);
//                return params;
//            }
//
//        };
//        Controller.getInstance().getRequestQueue().add(jsonObjRequest);
//    }




    ///-----------dummy code----///

    public void register(final String name ,final String email, final String passwd, final String mobile , final String city, final String locality, final String indus,

                          final String exp){
    String url = "http://www.jaldijob.in/test/test/v1/contact";
    VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
        @Override

        public void onResponse(NetworkResponse response) {
          //  progressDialog.dismiss();
            String resultResponse = new String(response.data);
            try {
             //   setData(resultResponse);



                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<JobUserData>>(){}.getType();
                Collection<JobUserData> enums = gson.fromJson(resultResponse,collectionType);
                JobUserData[] jud = enums.toArray(new JobUserData[enums.size()]);
              //  Log.v("user id came",jud[0].getId());
                Log.v("just name data",jud[0].getName());
                String userid = jud[0].getId();
                String userEmail = jud[0].getEmail();
                String userName = jud[0].getName();
                Log.v("Email",userEmail);
                Log.v("Name",userName);
                Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).storeRegisterUser(new JobUserData(userid,userName ,userEmail ));
            //    Log.v("just email data",jud[2].getEmail());
            //    Log.v("just mobile data",jud[3].getMobile());
            //    Log.v("just city data",jud[4].getLocation());
              //  Log.v("just locality data",jud[0].getId());

if(userid !=null){

    Intent inten = new Intent(getApplicationContext(),Registration_two.class);
    startActivity(inten);
    finish();

}




            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
           // progressDialog.dismiss();
           // Toast.makeText(getActivity(), "Failed to add", Toast.LENGTH_SHORT).show();
            NetworkResponse networkResponse = error.networkResponse;
            String errorMessage = "Unknown error";
            if (networkResponse == null) {
                if (error.getClass().equals(TimeoutError.class)) {
                    errorMessage = "Request timeout";
                }
            } else {
                String result = new String(networkResponse.data);
                try {
                    JSONObject response = new JSONObject(result);
                    String status = response.getString("status");
                    String message = response.getString("message");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.i("Error", errorMessage);
            error.printStackTrace();
        }
    }) {
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();

                /*sumeeth code for addding car*/
          //put parameters

            params.put("username", name);
            params.put("email",email);
            params.put("password",passwd);
            params.put("mobile",mobile);
            params.put("city",city);
            params.put("locality",locality);
            params.put("industry",indus);
            params.put("experence",exp);


            return params;
        }





    };


        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    Controller.getInstance().addToRequestQueue(multipartRequest);
}///addcar method





//                Intent intent=new Intent(getActivity(),Registration_two.class);
//
//                startActivity(intent);

                // Toast.makeText(getActivity(),"kkkkkk",Toast.LENGTH_LONG).show();


    private void setData(String response) {
        try {


            Type listOfTestObject = new TypeToken<List<JobUserData>>() {

            }.getType();



            Gson gson = new Gson();
            ArrayList<JobUserData> jobList = gson.fromJson(response,listOfTestObject);
            if (jobList == null || jobList.size() == 0) {
                //TODO
                /*
                * check whether it has some data or not ?
                *
                *
                * */



            } else {
                /*TODO:set the adapter of recyclerview .
                *
                *
                * */

              //  adapter = new JobDataAdapter(jobList,SearchResult.this);
              //  recyclerView.setAdapter(adapter);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        getLocation = location.get(position);
        getExperince = experince.toString();
        Log.e( "onItemSelected: ", getLocation);
        Log.e( "onItemSelected: ", getExperince);


    }
}
