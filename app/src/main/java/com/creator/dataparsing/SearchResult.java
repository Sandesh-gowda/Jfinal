package com.creator.dataparsing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.creator.dataparsing.adapter.JobDataAdapter;
import com.creator.dataparsing.model.JobData;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 23/08/16.
 */
public class SearchResult  extends AppCompatActivity{

    private RecyclerView recyclerView;
    private ArrayList<JobData> arrayList;
    private JobDataAdapter adapter;
    private String responseData;
    public String searchUrl ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);
        initRecyclerView();
        String s= getIntent().getStringExtra("skill");
        String loc = getIntent().getStringExtra("loc");
        String exp = getIntent().getStringExtra("exp");
        String sal  = getIntent().getStringExtra("sal");

        makeSearchCall(s,loc,sal,exp);







    }

    private void initRecyclerView() {
        arrayList = new ArrayList<>();


        recyclerView = (RecyclerView)
                findViewById(R.id.menu_recycler_view);

        recyclerView
                .setLayoutManager(new GridLayoutManager(SearchResult.this, 1));// Here 2 is no. of columns to be displayed


    }


    private void makeSearchCall(final String skill, final String area, final String salary, final String expe) {

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                "http://www.jaldijob.in/test/test/v1/generic",
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

                params.put("area", area);
                params.put("skill",skill);
                params.put("salary",salary);
                params.put("expe",expe);
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

                adapter = new JobDataAdapter(jobList,SearchResult.this);
                recyclerView.setAdapter(adapter);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

}
