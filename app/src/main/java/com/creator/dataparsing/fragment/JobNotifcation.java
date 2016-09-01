package com.creator.dataparsing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creator.dataparsing.R;

/**
 * Created by admin on 02/07/16.
 */
public class JobNotifcation extends Fragment {

View view;

  public JobNotifcation(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_of_shortlisted_job, container, false);
        //initRecyclerView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // fetchDataTask();
    }
}
