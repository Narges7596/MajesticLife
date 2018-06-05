package com.farazannajmi.majesticlife.SkillPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.R;

public class SkillsActivity extends AppCompatActivity
{
    private ListView skills_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        //getting UI elements
        skills_listView = (ListView) findViewById(R.id.SkillsActivity_ListView);

        //create our new array adapter
        ArrayAdapter<Skill> adapter = new SkillsListItemArrayAdapter(this, DataHolder.Skills);

        //bind the list view with the custom adapter
        skills_listView.setAdapter(adapter);
    }
}
