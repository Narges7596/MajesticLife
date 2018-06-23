package com.farazannajmi.majesticlife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class LoadingActivity extends AppCompatActivity
{
    public static boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        isFirstTime = sharedPref.getBoolean("FirstTime", true);

        DataHolder.InitialDataStructures();
        DataHolder.LoadData(this, this);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               // Log.d("Data", "Database Initialed!" + DataHolder.ThisUser.getUsername());

                Intent MainMenuIntent = new Intent(LoadingActivity.this, MainMenuActivity.class);
                startActivity(MainMenuIntent);
            }
        }, 2000);
    }
}
