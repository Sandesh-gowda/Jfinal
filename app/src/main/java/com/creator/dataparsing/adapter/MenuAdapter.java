package com.creator.dataparsing.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creator.dataparsing.R;
import com.creator.dataparsing.helper.ImgHelper;
import com.creator.dataparsing.helper.OnitemClickLIstener;
import com.creator.dataparsing.helper.RecyclerView_OnClickListener;
import com.creator.dataparsing.holder.ViewHolder;
import com.creator.dataparsing.model.Menu;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by admin on 01/07/16.
 *
 */
public class MenuAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final LayoutInflater mInflater;
    private ArrayList<Menu> menuList;
    private Context context;
    private OnitemClickLIstener onItemClickListener;


    public MenuAdapter( ArrayList<Menu> menuList, Context context) {

        this.menuList = menuList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.test_card_layout, viewGroup, false);
        return new ViewHolder(mainGroup);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




        Log.d("MenuAdapter", "onBindViewHolder : " + position);
        final Menu model = menuList.get(position);

        holder.itemDescription.setText(model.getDescription());
        holder.itemCost.setText(model.getMenu_price());
        holder.itemName.setText(model.getMenuItemName());


        //image loading method

       // holder.itemImage = model.getImage();
     //   if (imageList != null) {
            ImgHelper.loadImage(context, model.getImage(), holder.itemImage);
     //   }

        holder.setClickListener(new RecyclerView_OnClickListener.OnClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.item_card:
                    //    Intent in = new Intent(context, MenuDetailActivity.class);
                    //    in.putExtra("Car_Data", menuList.get(position));
                    //    context.startActivity(in);
                        break;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != menuList ? menuList.size() : 0);
    }
}
