package com.creator.dataparsing.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import com.creator.dataparsing.MainActivity;
import com.creator.dataparsing.R;
import com.creator.dataparsing.VolleyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 23/08/16.
 */

//
public class Registration_six extends AppCompatActivity implements View.OnClickListener{

EditText skill ;
    LinearLayout procced ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skill_registration);
        initViews();

    }


    public void initViews(){

        skill = (EditText)findViewById(R.id.skills_edit);
        procced = (LinearLayout)findViewById(R.id.submit_btn_skils);
        procced.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.submit_btn_skils:
                  callNetwork();
                break;
            case R.id.profile_pic:
                // openFileSelector(true);
                //callNetwork();
                //call image to pule from the file system

                Toast.makeText(getApplicationContext(),"UPload profile pic",Toast.LENGTH_SHORT).show();
                break;
        }

    }



    private void callNetwork() {



        String id="521";
        String Skill = skill.getText().toString();

        callData(id,Skill);


    }

    private void callData(final String id,final  String skill) {



        String url = "http://www.jaldijob.in/test/test/v1/empskill";
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

                    Intent inten = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(inten);

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
                params.put("skill",skill);
                params.put("experience","1");




                return params;
            }

        };


        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Controller.getInstance().addToRequestQueue(multipartRequest);





    }
}
