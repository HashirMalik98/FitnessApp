package com.example.xrezut.personalfitnessapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Thread timeout = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3000);
                    checkSharedPrefStatus();
                    finish();
                }catch (InterruptedException e){
                    System.out.println("Splash screen thread error");
                }
            }
        };
        timeout.start();
    }

    public void checkSharedPrefStatus(){
        SharedPreferences sharedPref = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        Boolean status = sharedPref.getBoolean("appAlreadyLaunched", false);

        if(status==true){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(), UserDetails.class);
            startActivity(intent);
        }
    }
}