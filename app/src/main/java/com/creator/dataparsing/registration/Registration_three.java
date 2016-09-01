package com.creator.dataparsing.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.creator.dataparsing.Controller;
import com.creator.dataparsing.MainActivity;
import com.creator.dataparsing.R;
import com.creator.dataparsing.VolleyMultipartRequest;
import com.creator.dataparsing.model.JobUserData;
import com.creator.dataparsing.preference.Spmanger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 23/08/16.
 */
//skills
public class Registration_three extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{


    EditText compny, desgnation;
    LinearLayout save,procced;
    AutoCompleteTextView  salary;
    ArrayAdapter<String> adapter;
    String salay [] = {"0","1 lakh","2 lakh","3 lakh","4 lakh","4 lakh","6 lakh"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_designation);
        initViews();

    }
    private void initViews()
    {

        compny = (EditText)findViewById(R.id.job_profile_company_name);
        desgnation = (EditText)findViewById(R.id.job_profile);
        salary = (AutoCompleteTextView) findViewById(R.id.salary_area_field);
        adapter = new ArrayAdapter<String>(Registration_three.this,android.R.layout.simple_dropdown_item_1line,salay);
        salary.setThreshold(1);
        salary.setAdapter(adapter);
        salary.setOnItemSelectedListener(this);
        // course = (EditText)findViewById(R.id.passout_yr_field);

        procced = (LinearLayout)findViewById(R.id.submit_btn_currentdesignation);
        save  =(LinearLayout)findViewById(R.id.skip_btn_crentdesignation);
        procced.setOnClickListener(this);
        save.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.submit_btn_currentdesignation:
                callNetwork();
                break;
            case R.id.skip_btn_crentdesignation:
                callNetwork();
                break;
        }

    }



    private void callNetwork() {

        String Ccompny = compny.getText().toString();
        String des = desgnation.getText().toString();
        String sal = salary.getText().toString();
      //  String co = course.getText().toString();
        JobUserData user = Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).getUser();
        Log.v("User Id", user.getId());



        callData(user.getId(),Ccompny,des,sal);


    }

    private void callData( final String id, final String ccompny, final String des, final String sal) {

        String url = "http://www.jaldijob.in/test/test/v1/desgnation";
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override

            public void onResponse(NetworkResponse response) {
                //  progressDialog.dismiss();
                String resultResponse = new String(response.data);
                try {
                    //  setData(resultResponse);
                    //   JSONObject result = new JSONObject(resultResponse);

                    Log.v("volley",resultResponse);
                    // String message = result.getString("Message");

                    // Log.d("MEssage",message);
                    //    String x =  result.getString("id");
                    //    Log.d("Error",x);

                    //  if(x != null){
                    //      Toast.makeText(getApplicationContext(),"Sorry email found",Toast.LENGTH_LONG).show();
                    // }else {


                    //{"Message":"Done procced further","Error":"false"} // message .

                    Intent inten = new Intent(getApplicationContext(),Registration_four.class);
                    startActivity(inten);
                    finish();

                    //  }


                    // {"Message":"Mobile number already registerd","Error":"false"}
//                }else {
//
//                }

//                    String message = result.getString("message");

                    /*if (status.equals(Constant.REQUEST_SUCCESS)) {
                        // tell everybody you have succed upload image and post strings
                        Log.i("Messsage", message);
                    } else {
                        Log.i("Unexpected", message);
                    }*/


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

                params.put("uid",id);
                params.put("company",ccompny);
                params.put("designation",des);
                params.put("salary",sal);
               // params.put("course",co);



                return params;
            }





        };


        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Controller.getInstance().addToRequestQueue(multipartRequest);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
