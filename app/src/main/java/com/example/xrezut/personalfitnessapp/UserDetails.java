package com.example.xrezut.personalfitnessapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class UserDetails extends AppCompatActivity {

    EditText username;
    EditText userage;
    EditText userheight;
    EditText userweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        username = (EditText) findViewById(R.id.nameinput);
        userage = (EditText) findViewById(R.id.ageinput);
        userheight = (EditText) findViewById(R.id.heightinput);
        userweight = (EditText) findViewById(R.id.weightinput);
    }

    public void saveInfo(View view){
        SharedPreferences sharedPref = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        String sUsername = username.getText().toString();
        String sAge = userage.getText().toString();
        String sHeight = userheight.getText().toString();
        String sWeight = userweight.getText().toString();

        if (sUsername.matches("") || (sAge.matches("")) ||
                (sHeight.matches("") || (sWeight.matches("")))){
            Toast.makeText(this, "Please enter all details.", Toast.LENGTH_LONG).show();
            return;
        }else{
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("name", username.getText().toString());
            editor.putString("age", userage.getText().toString());
            editor.putString("height", userheight.getText().toString());
            editor.putString("weight", userweight.getText().toString());
            editor.putBoolean("appAlreadyLaunched", true);
            editor.apply();
            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_LONG).show();
            emptyTextFields();
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            setContentView(R.layout.activity_main);
        }
    }

    public void emptyTextFields(){
        username.setText(null);
        userage.setText(null);
        userheight.setText(null);
        userweight.setText(null);
    }

    public void getData(){
        SharedPreferences sharedPref = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        String name = sharedPref.getString("name", ""); //returns name
    }
}
