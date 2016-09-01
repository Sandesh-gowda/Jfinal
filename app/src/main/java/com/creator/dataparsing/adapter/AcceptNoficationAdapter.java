package com.creator.dataparsing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.creator.dataparsing.R;
import com.creator.dataparsing.helper.OnitemClickLIstener;
import com.creator.dataparsing.helper.RecyclerView_OnClickListener;
import com.creator.dataparsing.holder.NotificationHolder;
import com.creator.dataparsing.model.JobNotification;

import java.util.ArrayList;

/**
 * Created by admin on 28/08/16.
 */
public class AcceptNoficationAdapter extends RecyclerView.Adapter<NotificationHolder> {
    private final LayoutInflater mInflater;
    private ArrayList<JobNotification> jobNotificationsList;
    private Context context;
    private OnitemClickLIstener onItemClickListener;

    public AcceptNoficationAdapter(ArrayList<JobNotification> jobNotifications, Context context){

        this.jobNotificationsList = jobNotifications;
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }


    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.inbox_notification, viewGroup, false);
        return new NotificationHolder(mainGroup);
    }

    @Override
    public void onBindViewHolder(final NotificationHolder holder, int position) {

        Log.d("Notifcaton ", "onBindViewHolder : " + position);
        final JobNotification modelNotificaton = jobNotificationsList.get(position);
        holder.company_name_notification.setText(modelNotificaton.getCompanyName());
        holder.salary_notification.setText(modelNotificaton.getAnnualSalary());
        holder.designation_notification.setText(modelNotificaton.getJobtitle());
        holder.skill_notifcation.setText(modelNotificaton.getMinexp()+ " "+"Experience");

holder.ignor_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        holder.ignor_button.setText("Done");
        holder.intrested_button.setClickable(false);
        holder.ignor_button.setVisibility(View.VISIBLE);

    }
});

        holder.intrested_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.intrested_button.setText("Accepted");
                holder.ignor_button.setClickable(false);
                holder.intrested_button.setVisibility(View.VISIBLE);

            }
        });

//        holder.setClickListener(new RecyclerView_OnClickListener.OnClickListener() {
//            @Override
//            public void OnItemClick(View view, int position) {
//                switch (view.getId()) {
//                    case R.id.intrested_button:
//                        Toast.makeText(context,"Thanks for Interest",Toast.LENGTH_LONG).show();
//
//                        break;
//                    case R.id.ignor_button:
//                        Toast.makeText(context,"Thanks for the response",Toast.LENGTH_LONG).show();
//                        break;
//
//
//                }
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return (null != jobNotificationsList ? jobNotificationsList.size() : 0);
    }
}
