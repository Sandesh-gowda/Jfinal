package com.creator.dataparsing.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creator.dataparsing.R;
import com.creator.dataparsing.helper.RecyclerView_OnClickListener;

/**
 * Created by admin on 01/07/16.
 */
public class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public TextView jobTitle, jobCompany, jobExperience, jobSkills ,jobLocation ,jobSalary;
    public ImageView itemImage ;
    public CardView cardView ;
    private RecyclerView_OnClickListener.OnClickListener onClickListener;
 public LinearLayout applyButton,card_view;

    public JobViewHolder(View view){
        super(view);

        this.jobTitle = (TextView) view.findViewById(R.id.title_jaldi_job);
        this.jobCompany = (TextView) view.findViewById(R.id.company);
        this.jobExperience = (TextView) view.findViewById(R.id.experience);
        this.jobSkills = (TextView)view.findViewById(R.id.skills);
        this.jobLocation = (TextView)view.findViewById(R.id.location);
        this.jobSalary = (TextView)view.findViewById(R.id.salary);
        this.applyButton = (LinearLayout)view.findViewById(R.id.apply);
        this.card_view = (LinearLayout)view.findViewById(R.id.card_view);

       // this.itemImage = (ImageView) view.findViewById(R.id.item_image);
      //  this.cardView = (CardView) view.findViewById(R.id.item_card);


        //this.cardView.setOnClickListener(this);

        this.applyButton.setOnClickListener(this);
        this.card_view.setOnClickListener(this);
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
