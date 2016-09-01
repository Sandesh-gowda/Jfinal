package com.creator.dataparsing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creator.dataparsing.registration.login;

/**
 * Created by Admin on 8/30/2016.
 */
public class forgotpassword extends AppCompatActivity {

public TextView submitPassword ;
    public EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        password = (EditText) findViewById(R.id.forgotPassword);
        submitPassword = (TextView) findViewById(R.id.submitPassword);


        submitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Your password will be reseted",Toast.LENGTH_LONG).show();
                Intent  i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
                finish();

            }
        });







    }
}
