package com.farazannajmi.majesticlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SkillsActivity extends AppCompatActivity
{
    public AppManager TheAppManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        TheAppManager = (AppManager) getApplicationContext();
    }
}
