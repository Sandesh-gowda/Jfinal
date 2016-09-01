package com.creator.dataparsing.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.creator.dataparsing.Controller;
import com.creator.dataparsing.HomeSearch;
import com.creator.dataparsing.R;
import com.creator.dataparsing.adapter.JobDataAdapter;
import com.creator.dataparsing.model.JobData;
import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 02/07/16.
 */
public class Home extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<JobData> arrayList;
    private JobDataAdapter adapter;
    private FloatingActionButton mFab;


    private String responseData;
    public String URLMENU = "http://jaldijob.in/test/test/v1/getjob";
    View view;



   public Home(){

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_list, container, false);
        initRecyclerView();
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
//        //initRecyclerView();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), HomeSearch.class);
                startActivity(in);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         makeSearchCall();
    }




    private void initRecyclerView() {
        arrayList = new ArrayList<>();

//fab


        recyclerView = (RecyclerView)
                view.findViewById(R.id.menu_recycler_view);

        recyclerView
                .setLayoutManager(new GridLayoutManager(getContext(), 1));// Here 2 is no. of columns to be displayed


    }


    private void makeSearchCall() {
        StringRequest jsonObjRequest = new StringRequest(Request.Method.GET,
                "http://www.jaldijob.in/test/test/v1/getjob/",
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
        });
        jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
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

                adapter = new JobDataAdapter(jobList,getContext());
                recyclerView.setAdapter(adapter);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }


}
