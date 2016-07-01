package com.creator.dataparsing.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.creator.dataparsing.R;
import com.creator.dataparsing.helper.RecyclerView_OnClickListener;

/**
 * Created by admin on 01/07/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public TextView itemDescription, itemCost, itemName ;
    public ImageView itemImage ;
    public CardView cardView ;
    private RecyclerView_OnClickListener.OnClickListener onClickListener;


    public ViewHolder(View view){
        super(view);

        this.itemCost = (TextView) view.findViewById(R.id.text_cost);
        this.itemDescription = (TextView) view.findViewById(R.id.item_discrp);
        this.itemName = (TextView) view.findViewById(R.id.item_name);
        this.itemImage = (ImageView) view.findViewById(R.id.item_image);
        this.cardView = (CardView) view.findViewById(R.id.item_card);


        this.cardView.setOnClickListener(this);
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
