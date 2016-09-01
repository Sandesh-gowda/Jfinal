package com.creator.dataparsing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.creator.dataparsing.model.Locality_json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by admin on 23/08/16.
 */
public class HomeSearch extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //need to initaite the textinputlayout;

    TextInputLayout tilSkill, tilLocation, tilExperience, tilSalary ;
    AutoCompleteTextView mSkill, mLocation, mExprience, mSalary,mdistance;

    TextView buttonSearch ;
    private static FragmentManager fragmentManager;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter_loc;
    ArrayAdapter<String> adapter_ind;
    ArrayAdapter<String> adapter_sal;
    ArrayAdapter<String> adapter_dis;
    String[] experince = {"Fresher","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
    String [] salary = {"0","1 lakh","2 lakh","3 lakh","4 lakh","5 lakh","6 lakh"};
    String [] distance={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50"};
    public static ArrayList<String> location = new ArrayList<>();
    String [] industry = {"Internet Jobs","HR/Admin Jobs","Delivery Jobs","House Keeping Jobs","Restaurant Jobs","Security Guard Jobs","Legal Jobs","Marketing Jobs","Finance Jobs","Cashier Jobs","Data Entry Jobs","BPO Jobs","Engineering Jobs","Driver Jobs","Beautician Jobs","Tailor Jobs"};
    String getLocation,getExperince;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_search_jaldijob);


        fragmentManager = getSupportFragmentManager();

        initViews();
        try {
            locality();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //hint for the layout
String getSkill = mSkill.getText().toString();
        String getLocation = mLocation.getText().toString();
        String getExprience = mExprience.getText().toString();
        String getSalary = mSalary.getText().toString();


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getSkill = mSkill.getText().toString();
                String getLocation = mLocation.getText().toString();
                String getExprience = mExprience.getText().toString();
                String getSalary = mSalary.getText().toString();
             //   Toast.makeText(getApplicationContext(),"Skill"+getSkill+" "+getLocation+" "+getExprience+" "+getSalary+" ",Toast.LENGTH_LONG).show();
              Intent myintent=new Intent(HomeSearch.this, SearchResult.class)

               .putExtra("skill", getSkill)
                       .putExtra("loc",getLocation)
                       .putExtra("exp",getExprience)
                       .putExtra("sal",getSalary)

                        ;

//
//                Bundle b = new Bundle();
//                b.putString("skill",getSkill);
//                b.putString("loc",getLocation);
//                b.putString("exp",getExprience);
//                b.putString("sal",getSalary);
//                AcceptNotification frgSearch = new AcceptNotification();
//                frgSearch.setArguments(b);
//                finish();

               startActivity(myintent);

validData();



            }
        });


        //code to send data from fragment to acti





    }


    public void validData(){
        String getSkill = mSkill.getText().toString();
        String getLocation = mLocation.getText().toString();
        String getExprience = mExprience.getText().toString();
        String getSalary = mSalary.getText().toString();

    }




   private void  initViews(){

       tilSkill = (TextInputLayout) findViewById(R.id.input_layout_keyword);
       tilLocation = (TextInputLayout) findViewById(R.id.input_layout_locaion);
       tilExperience = (TextInputLayout) findViewById(R.id.input_layout_experince);
       tilSalary = (TextInputLayout)findViewById(R.id.input_layout_salary);

//       tilSkill.setHint("BPO JOBS,MARKETING JOB");
//       tilLocation.setHint("HSR Layout, Kormangala");
//       tilExperience.setHint("1, 2 ");
//       tilSalary.setHint("1lakhs,2lakhs");



       mSkill = (AutoCompleteTextView) findViewById(R.id.keyword);

       buttonSearch = (TextView) findViewById(R.id.search_btn);


       mExprience= (AutoCompleteTextView) findViewById(R.id.min_exp_field);
       adapter = new ArrayAdapter<String>(HomeSearch.this,android.R.layout.select_dialog_item,experince);
       mExprience.setThreshold(1);
       mExprience.setAdapter(adapter);
       mExprience.setOnItemClickListener(this);

       mLocation = (AutoCompleteTextView) findViewById(R.id.location);
       adapter_loc = new ArrayAdapter<String>(HomeSearch.this,android.R.layout.select_dialog_item,location);
       mLocation.setThreshold(1);
       mLocation.setAdapter(adapter_loc);
       mLocation.setOnItemClickListener(this);

       mSalary = (AutoCompleteTextView) findViewById(R.id.min_salery_field);
       adapter_sal = new ArrayAdapter<String>(HomeSearch.this,android.R.layout.select_dialog_item,salary);
       mSalary.setThreshold(1);
       mSalary.setAdapter(adapter_sal);
       mSalary.setOnItemClickListener(this);

       mdistance = (AutoCompleteTextView) findViewById(R.id.range_distance);
       adapter_dis = new ArrayAdapter<String>(HomeSearch.this,android.R.layout.select_dialog_item,distance);
       mdistance.setThreshold(1);
       mdistance.setAdapter(adapter_dis);
       mdistance.setOnItemClickListener(this);






   }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
}