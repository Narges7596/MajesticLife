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
    public static boolean isDataLoaded = false;
    public static boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        isDataLoaded = false;
        isFirstTime = true;

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        isFirstTime = sharedPref.getBoolean("FirstTime", true);

        //loading data
        DataHolder.InitialDataStructures();
        DataHolder.LoadUser(this, this);

        //calling this every 1 second so that if data has been loaded then load new scene
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                if(isDataLoaded)
                {
                    Log.d("WorkFlow", "Loading new activity for " + DataHolder.ThisUser.getUsername());

                    Intent MainMenuIntent = new Intent(LoadingActivity.this, MainMenuActivity.class);
                    startActivity(MainMenuIntent);
                }
                else
                {
                    handler.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
