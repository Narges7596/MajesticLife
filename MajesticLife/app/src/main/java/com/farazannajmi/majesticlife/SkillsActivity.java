package com.farazannajmi.majesticlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SkillsActivity extends AppCompatActivity
{
    public AppManager TheAppManager;

    private ListView skills_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);


        TheAppManager = MainMenuActivity.getTheAppManager();

        //getting UI elements
        skills_listView = (ListView) findViewById(R.id.SkillsActivity_ListView);

        //region ----------------------------test------------------------------
        Skill s1 = new Skill("Knowledge", 5, 50);
        TheAppManager.Skills.add(s1);

        Skill s2 = new Skill("HandCrafting", 10, 25);
        TheAppManager.Skills.add(s2);
        //endregion

        //create our new array adapter
        ArrayAdapter<Skill> adapter = new SkillsListItemArrayAdapter(this, TheAppManager.Skills);

        //bind the list view with the custom adapter
        skills_listView.setAdapter(adapter);
    }
}
