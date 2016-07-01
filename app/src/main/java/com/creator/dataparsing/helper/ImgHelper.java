package com.creator.dataparsing.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.creator.dataparsing.R;
import com.squareup.picasso.Picasso;

/**
 * Created by admin on 01/07/16.
 */
public class ImgHelper {

    public static Picasso picasso;
    private static boolean isPicassoEnabled;


    public static void loadImage(Context ctx, String imageUrl, final ImageView imageView) {
//        if (isPicassoEnabled) {
//            picasso.load(imageUrl).into(imageView);
//        } else {
//            Glide.with(ctx).load(imageUrl).into(imageView);
//        }
        loadImage(ctx, imageUrl, R.drawable.buger, R.drawable.buger, imageView);
    }

    public static void loadImage(Context ctx, String imageUrl, int placeHolder, int errorPlaceHolder, final ImageView imageView) {
        if (isPicassoEnabled) {
            picasso.load(imageUrl).placeholder(placeHolder).error(errorPlaceHolder).into(imageView);
        } else {
            Glide.with(ctx).load(imageUrl).placeholder(placeHolder).error(errorPlaceHolder).into(imageView);
        }
    }
}
