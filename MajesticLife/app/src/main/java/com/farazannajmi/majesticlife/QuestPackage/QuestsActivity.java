package com.farazannajmi.majesticlife.QuestPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.farazannajmi.majesticlife.DataStructures.Quest;
import com.farazannajmi.majesticlife.R;

public class QuestsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quests);
        Quest q = new Quest();
    }
}
