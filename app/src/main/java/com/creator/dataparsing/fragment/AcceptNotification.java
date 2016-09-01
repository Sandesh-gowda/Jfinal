package com.creator.dataparsing.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.creator.dataparsing.Controller;
import com.creator.dataparsing.HomeSearch;
import com.creator.dataparsing.R;
import com.creator.dataparsing.SearchResult;
import com.creator.dataparsing.adapter.AcceptNoficationAdapter;
import com.creator.dataparsing.adapter.JobDataAdapter;
import com.creator.dataparsing.model.JobData;
import com.creator.dataparsing.model.JobNotification;
import com.creator.dataparsing.model.JobUserData;
import com.creator.dataparsing.preference.Spmanger;
import com.github.clans.fab.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 25/08/16.
 */
public class AcceptNotification extends Fragment {


    private RecyclerView recyclerView;
    private ArrayList<JobNotification> arrayList;
    private AcceptNoficationAdapter adapter;
    View view;
    //fab
    private FloatingActionButton mFab;


    public AcceptNotification(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.notifcation, container, false);
        initRecyclerView();

      //  view = inflater.inflate(R.layout.home_search_jaldijob, container, false);
      // mFab = (FloatingActionButton) view.findViewById(R.id.fab);
//        //initRecyclerView();
//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent in = new Intent(getContext(), HomeSearch.class);
//                startActivity(in);
//            }
//        });



return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // fetchDataTask();
        makeSearchCall();
        initViews();


//
//        String getSkill = mSkill.getText().toString();
//        String getLocation = mLocation.getText().toString();
//        String getExprience = mExprience.getText().toString();
//        String getSalary = mSalary.getText().toString();



    }

    private void  initViews(){

//        tilSkill = (TextInputLayout) view.findViewById(R.id.input_layout_keyword);
//        tilLocation = (TextInputLayout) view.findViewById(R.id.input_layout_locaion);
//        tilExperience = (TextInputLayout) view.findViewById(R.id.input_layout_experince);
//        tilSalary = (TextInputLayout)view.findViewById(R.id.input_layout_salary);
//
//
//
//
//
//        mSkill = (EditText) view.findViewById(R.id.keyword);
//
//        mLocation = (EditText) view.findViewById(R.id.location);
//
//        mExprience= (EditText) view.findViewById(R.id.min_exp_field);
//
//        mSalary = (EditText) view.findViewById(R.id.min_salery_field);
//
//        buttonSearch = (TextView) view.findViewById(R.id.search_btn);
//        buttonSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String getSkill = mSkill.getText().toString();
//                String getLocation = mLocation.getText().toString();
//                String getExprience = mExprience.getText().toString();
//                String getSalary = mSalary.getText().toString();
//             //   Toast.makeText(getApplicationContext(),"Skill"+getSkill+" "+getLocation+" "+getExprience+" "+getSalary+" ",Toast.LENGTH_LONG).show();
//              Intent myintent=new Intent(getContext(), SearchResult.class)
//
//               .putExtra("skill", getSkill)
//                       .putExtra("loc",getLocation)
//                       .putExtra("exp",getExprience)
//                       .putExtra("sal",getSalary)
//
//                        ;
//                startActivity(myintent);
//
//
//    }
//
//});

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
        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,

                "http://www.jaldijob.in/test/test/v1/acceptnotify",
                new Response.Listener<String>() {



                    @Override
                    public void onResponse(String response) {
                        String responseData = response;
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
                JobUserData user = Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).getUser();
                Log.v("Delaer Id", user.getId());


                params.put("userid",  user.getId());

                return params;
            }

        };
        jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Controller.getInstance().getRequestQueue().add(jsonObjRequest);

    }



    private void setData(String response) {
        try {


            Type listOfTestObject = new TypeToken<List<JobNotification>>() {
            }.getType();



            Gson gson = new Gson();
            ArrayList<JobNotification> jobList = gson.fromJson(response,listOfTestObject);
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

                adapter = new AcceptNoficationAdapter(jobList,getContext());
                recyclerView.setAdapter(adapter);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
}
