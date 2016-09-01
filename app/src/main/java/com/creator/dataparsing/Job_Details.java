package com.creator.dataparsing;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.creator.dataparsing.adapter.JobDataAdapter;
import com.creator.dataparsing.model.JobData;
import com.creator.dataparsing.model.JobUserData;
import com.creator.dataparsing.preference.Spmanger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vsg on 8/31/2016.
 */
public class Job_Details extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    ActionBar action;
    TextView toolbar_title,submit_btn_tv;
    TextView detail_job_title,job_type,exp,loclity,salark,qualification,skill,posted_date,job_description,industry,full_time,vac,keyword,address;

    //get data from here
    private JobData jobData_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobdetails_custom_view);
jobData_model = (JobData) getIntent().getSerializableExtra("JobDetail") ;
        intiDetail();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_title = (TextView) findViewById(R.id.toolbar_name);
        submit_btn_tv = (TextView) findViewById(R.id.submit_btn_tv);
        detail_job_title  =(TextView) findViewById(R.id.detail_job_title) ;
        submit_btn_tv.setOnClickListener(this);
        toolbar_title.setText("Job Details");
        setJobData();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        action = getSupportActionBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {

            action.setDisplayHomeAsUpEnabled(true);
            action.setHomeButtonEnabled(true);

        }


    }


    public void intiDetail(){
        job_type = (TextView)findViewById(R.id.detail_job_type);
        exp  = (TextView)findViewById(R.id.detail_job_maxexp);
      loclity  = (TextView)findViewById(R.id.detail_job_locality);
        salark= (TextView)findViewById(R.id.detail_job_annual_salary);
       qualification = (TextView)findViewById(R.id.detail_job_qualification);
        skill= (TextView)findViewById(R.id.detail_job_skill);
        posted_date= (TextView)findViewById(R.id.detail_posted_date);
        job_description= (TextView)findViewById(R.id.detail_job_desc);
        industry= (TextView)findViewById(R.id.detail_industry);
        full_time= (TextView)findViewById(R.id.detail_job_type_);
        vac= (TextView)findViewById(R.id.detail_job_vacancy_num);
       keyword = (TextView)findViewById(R.id.detail_job_keywords);
        address= (TextView)findViewById(R.id.detaik_job_jobaddress);
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())

        {
            case R.id. submit_btn_tv:

                Toast.makeText(this,"Successfully Applied",Toast.LENGTH_SHORT).show();
                String jobid = jobData_model.getId();
                JobUserData user = Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).getUser();
                String uid = user.getId();
                applyJob(uid,jobid);
                break;


        }
    }


    public void setJobData(){
        detail_job_title.setText(jobData_model.getJobTitle());
        job_type.setText(jobData_model.getIndustry());
        exp.setText(jobData_model.getMinexp());
        loclity.setText(jobData_model.getLocation());
        salark.setText(jobData_model.getAnnualSalary());
        qualification.setText(jobData_model.getQualification());
        skill.setText(jobData_model.getSkills());
        posted_date.setText(jobData_model.getCreatedDate());
        job_description.setText(jobData_model.getJobDesc());
        industry.setText(jobData_model.getIndustry());
        full_time.setText(jobData_model.getJobTitle());
        vac.setText(jobData_model.getVacancy_num());
        keyword.setText(jobData_model.getKeywords());
        address.setText(jobData_model.getJobaddress());
    }
    public  void applyJob(final String userid, final String jobid){

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                "http://www.jaldijob.in/test/test/v1/applyjob",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("JobApplied", "onResponse : " + response);
                        Toast.makeText(getApplicationContext(), "Successfully Applied", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "failed to Apply job", Toast.LENGTH_SHORT).show();

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
