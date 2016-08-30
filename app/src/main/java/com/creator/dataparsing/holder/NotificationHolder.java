package com.creator.dataparsing.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.creator.dataparsing.R;
import com.creator.dataparsing.helper.RecyclerView_OnClickListener;

/**
 * Created by admin on 28/08/16.
 */
public class NotificationHolder  extends RecyclerView.ViewHolder implements View.OnClickListener  {

    //

  public   TextView company_name_notification,salary_notification,designation_notification,skill_notifcation,ignor_button,intrested_button;
    private RecyclerView_OnClickListener.OnClickListener onClickListener;

    public NotificationHolder(View view){
        super(view);
this.company_name_notification = (TextView)view.findViewById(R.id.company_name_notification);
       this.salary_notification = (TextView)view.findViewById(R.id.salary_notification);
   this.designation_notification     = (TextView)view.findViewById(R.id.designation_notification);
     this.skill_notifcation   = (TextView)view.findViewById(R.id.skill_notifcation);
     this.ignor_button   = (TextView)view.findViewById(R.id.ignor_button);
     this.intrested_button   = (TextView)view.findViewById(R.id.intrested_button);


       // this.intrested_button.setOnClickListener(this);
       // this.ignor_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (onClickListener != null) {
            onClickListener.OnItemClick(view, getAdapterPosition());

        }
    }


    // Setter for listener
    public void setClickListener(
            RecyclerView_OnClickListener.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
