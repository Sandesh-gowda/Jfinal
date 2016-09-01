package com.creator.dataparsing.registration;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.creator.dataparsing.AppHelper;
import com.creator.dataparsing.Controller;
import com.creator.dataparsing.MainActivity;
import com.creator.dataparsing.R;
import com.creator.dataparsing.VolleyMultipartRequest;
import com.creator.dataparsing.model.JobUserData;
import com.creator.dataparsing.preference.Spmanger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 23/08/16.
 */

//resume
public class Registration_four extends AppCompatActivity implements View.OnClickListener{



    LinearLayout procced, uploadPic;

    public static final int REQUEST_CAMERA = 0, SELECT_FILE = 1, PICK_INSURANCE_REQUEST_CODE = 2, PICK_RCBOOK_REQUEST_CODE = 3;
    String picProfile;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_profile_pic);
        initViews();

    }

    private void initViews()
    {



        procced = (LinearLayout)findViewById(R.id.profile_pick_next);
        uploadPic  =(LinearLayout)findViewById(R.id.profile_pic);
        procced.setOnClickListener(this);
        uploadPic.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.profile_pick_next:
                callNetwork();
                break;
            case R.id.profile_pic:
                openFileSelector(true);
                //callNetwork();
                //call image to pule from the file system

                Toast.makeText(getApplicationContext(),"UPload profile pic",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void callNetwork() {

        JobUserData user = Controller.getInstance().getPrefManager(Spmanger.Login_Preferences).getUser();
        Log.v("Delaer Id", user.getId());

      //  String id="521";
String profilePic = picProfile;

        callData(user.getId(),profilePic);


    }


    private void callData(final String id, final String getprofilePic) {

        String url = "http://www.jaldijob.in/test/test/v1/pic";
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
            @Override

            public void onResponse(NetworkResponse response) {
                //  progressDialog.dismiss();
                String resultResponse = new String(response.data);
                try {
                    //  setData(resultResponse);
                    //   JSONObject result = new JSONObject(resultResponse);

                    Log.v("volley",resultResponse);
                    // String message = result.getString("Message");

                    // Log.d("MEssage",message);
                    //    String x =  result.getString("id");
                    //    Log.d("Error",x);

                    //  if(x != null){
                    //      Toast.makeText(getApplicationContext(),"Sorry email found",Toast.LENGTH_LONG).show();
                    // }else {


                    //{"Message":"Done procced further","Error":"false"} // message .

                    Intent inten = new Intent(getApplicationContext(),Registration_five.class);
                    startActivity(inten);
                    finish();

                    //  }


                    // {"Message":"Mobile number already registerd","Error":"false"}
//                }else {
//
//                }

//                    String message = result.getString("message");

                    /*if (status.equals(Constant.REQUEST_SUCCESS)) {
                        // tell everybody you have succed upload image and post strings
                        Log.i("Messsage", message);
                    } else {
                        Log.i("Unexpected", message);
                    }*/


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressDialog.dismiss();
                // Toast.makeText(getActivity(), "Failed to add", Toast.LENGTH_SHORT).show();
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
                    }
                } else {
                    String result = new String(networkResponse.data);
                    try {
                        JSONObject response = new JSONObject(result);
                        String status = response.getString("status");
                        String message = response.getString("message");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Error", errorMessage);
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                /*sumeeth code for addding car*/
                //put parameters

                params.put("uid",id);




                return params;
            } @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            protected Map<String, DataPart> getByteData() {


                Map<String, DataPart> params = new HashMap<>();

                File profilePic = null ;
                Bitmap bmpPic = null ;
                try {
                    Log.e("Profile Pic Image", getprofilePic);
                    if (!getprofilePic.equals("")) {
                        profilePic = new File(getprofilePic);

                        bmpPic = BitmapFactory.decodeFile(profilePic.getAbsolutePath());
                        params.put("pic", new DataPart(new File(getprofilePic).getName(), AppHelper.bitmapToByteArray(bmpPic), "image/jpeg"));
                    } else {
                        bmpPic = BitmapFactory.decodeResource(getResources(), R.drawable.a);
                        params.put("pic", new DataPart("a.jpg", AppHelper.bitmapToByteArray(bmpPic), "image/jpeg"));

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    bmpPic = BitmapFactory.decodeResource(getResources(), R.drawable.a);
                    params.put("pic", new DataPart("a.jpg", AppHelper.bitmapToByteArray(bmpPic), "image/jpeg"));


                }
                return params;
            }





        };


        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Controller.getInstance().addToRequestQueue(multipartRequest);


    }



    // CAMERA AND GALLERY

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                    // takePhoto();
                } else if (items[item].equals("Choose from Library")) {

                    //  startActivityForResult(new Intent(getActivity(), Gallery_Activity.class), CustomGallerySelectId);
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Log.e("Activity", "Result OK");
          //  Fragment home = getFragment(1);

                if (requestCode == REQUEST_CAMERA) {
                    //
                }
                if (requestCode == SELECT_FILE) {
                    //
                }
                if (requestCode == PICK_INSURANCE_REQUEST_CODE)


                    showInsuranceFilePath(data, this);
                if (requestCode == PICK_RCBOOK_REQUEST_CODE)


                    showInsuranceFilePath(data,this);



        } else
            Log.e("Activity", "Result Fail");
    }


    public void showInsuranceFilePath(Intent data, Context context) {


        String originalPath = getOriginalPath(data, context);
        picProfile = originalPath;
      //    ImageHelper.loadImage(this, originalPath, insurance_file_name);
    }




    private String getOriginalPath(Intent data, Context context) {
        Uri selectedImageUri = data.getData();
        Log.e("Select File", selectedImageUri.toString());
        String[] projection = {MediaStore.MediaColumns.DATA};
        CursorLoader cursorLoader = new CursorLoader(context, selectedImageUri, projection, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

        cursor.moveToFirst();

        return cursor.getString(column_index);
    }


    private void openFileSelector(boolean isPic) {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        if (isPic) {

            startActivityForResult(
                    Intent.createChooser(intent, "Select File"),
                    PICK_INSURANCE_REQUEST_CODE);

        }
    }
}
