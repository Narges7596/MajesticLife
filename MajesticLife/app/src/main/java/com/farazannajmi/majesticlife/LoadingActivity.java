package com.farazannajmi.majesticlife;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class LoadingActivity extends AppCompatActivity
{
    public static boolean LoadingDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        final AppCompatActivity context = this;

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }


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
