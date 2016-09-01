package com.creator.dataparsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.creator.dataparsing.adapter.JobDataAdapter;
import com.creator.dataparsing.model.JobData;
import com.creator.dataparsing.model.JobUserData;
import com.creator.dataparsing.preference.Spmanger;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 26/08/16.
 */
public class ListOfJobApplied  extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<JobData> arrayList;
    private String responseData;

    View view;
    private JobDataAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_applied_job);
        initRecyclerView();
        JobUserData user = Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).getUser();
        Log.v("Delaer Id", user.getId());


        makeSearchCall(user.getId());




    }
    private void initRecyclerView() {
        arrayList = new ArrayList<>();


        recyclerView = (RecyclerView)
                findViewById(R.id.frnd_list);

        recyclerView
                .setLayoutManager(new GridLayoutManager(ListOfJobApplied.this, 1));// Here 2 is no. of columns to be displayed


    }


    private void makeSearchCall(final String udi) {

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                "http://www.jaldijob.in/test/test/v1/jobapplied",
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        responseData = response;
                        setData(response);
                        VolleyLog.d("volley", "Error: " + response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();

                Log.d("MenuFragment", "onErrorResponse : 103");
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("userid", udi);

                return params;
            }

        };
        Controller.getInstance().getRequestQueue().add(jsonObjRequest);

    }



    private void setData(String response) {
        try {


            Type listOfTestObject = new TypeToken<List<JobData>>() {
            }.getType();



            Gson gson = new Gson();
            ArrayList<JobData> jobList = gson.fromJson(response,listOfTestObject);
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

                adapter = new JobDataAdapter(jobList,ListOfJobApplied.this);
                recyclerView.setAdapter(adapter);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }



}
