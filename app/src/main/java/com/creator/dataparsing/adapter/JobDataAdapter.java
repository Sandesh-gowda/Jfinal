package com.creator.dataparsing.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.creator.dataparsing.Controller;
import com.creator.dataparsing.Job_Details;
import com.creator.dataparsing.R;
import com.creator.dataparsing.helper.OnitemClickLIstener;
import com.creator.dataparsing.helper.RecyclerView_OnClickListener;
import com.creator.dataparsing.holder.JobViewHolder;
import com.creator.dataparsing.holder.ViewHolder;
import com.creator.dataparsing.model.JobData;
import com.creator.dataparsing.model.JobUserData;
import com.creator.dataparsing.preference.Spmanger;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 23/08/16.
 */
public class JobDataAdapter extends RecyclerView.Adapter<JobViewHolder> {


    private final LayoutInflater mInflater;
    private ArrayList<JobData> jobList;
    private Context context;
    private OnitemClickLIstener onItemClickListener;

  public   JobDataAdapter(ArrayList<JobData> jobList, Context context){

            this.jobList = jobList;
            this.context = context;
            mInflater = LayoutInflater.from(context);
        }



    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.card_view_jaldi, viewGroup, false);
        return new JobViewHolder(mainGroup);
    }

    /**
     *
     * @param holder
     * @param position
     *
     * this will hold the data which is getting populated here.
     */

    public void onBindViewHolder(JobViewHolder holder, int position) {





        Log.d("MenuAdapter", "onBindViewHolder : " + position);
        final JobData modelJob = jobList.get(position);

        //holder to hold the data
       holder.jobTitle.setText(modelJob.getJobTitle());
        holder.jobCompany.setText(modelJob.getIndustry()); // name of the company should come so that the data in the
        holder.jobExperience.setText(modelJob.getMinexp()+"Year");
        holder.jobLocation.setText(modelJob.getLocation());
        holder.jobSkills.setText(modelJob.getSkills());
        holder.jobSalary.setText(modelJob.getAnnualSalary()+"Lakh");


        holder.setClickListener(new RecyclerView_OnClickListener.OnClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.apply:
                        String jobid = modelJob.getId();
                        JobUserData user = Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).getUser();
                        String uid = user.getId();
                       // Toast.makeText(context, "Button clicked"+jobid+"User id "+uid+"", Toast.LENGTH_LONG).show();
                        Toast.makeText(context, "Thank you for applying", Toast.LENGTH_LONG).show();

                        applyJob(uid,jobid);
                        break;


                    case R.id.card_view:

                        Intent in = new Intent(context,Job_Details.class);
                        in.putExtra("JobDetail",jobList.get(position));
                        context.startActivity(in);
                        break;
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return (null != jobList ? jobList.size() : 0);
    }


    public  void applyJob(final String userid, final String jobid){

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                "http://www.jaldijob.in/test/test/v1/applyjob",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("JobApplied", "onResponse : " + response);
                        Toast.makeText(context, "Successfully Applied", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(context, "failed to Apply job", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userid", userid);
                params.put("jobid", jobid);
                return params;
            }

        };
        Controller.getInstance().getRequestQueue().add(jsonObjRequest);



    }


    }

