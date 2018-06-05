package com.farazannajmi.majesticlife.SkillPackage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.DataStructures.Skill_Time;
import com.farazannajmi.majesticlife.FaaliatPackage.EditFaaliatActivity;
import com.farazannajmi.majesticlife.FaaliatPackage.FaaliatsActivity;
import com.farazannajmi.majesticlife.R;

import java.util.ArrayList;

public class SkillsActivity extends AppCompatActivity
{
    public static Context context;
    public static ArrayAdapter<Skill> skill_gridview_adapter;

    private GridView skills_gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        context = getApplicationContext();

        //getting UI elements
        skills_gridview = (GridView) findViewById(R.id.SkillsActivity_gridview);

        //create our new array adapter
        skill_gridview_adapter = new SkillsListItemArrayAdapter(this, DataHolder.Skills);
        skills_gridview.setAdapter(skill_gridview_adapter);
    }

    public void UiElementsOnClick(View view)
    {
        if (view.getId() ==R.id.SkillsActivity_addSkill_btn)
        {
            Skill newSkill = new Skill("new skill", DataHolder.SkillAvatars.get(0),1,0);
            DataHolder.Skills.add(newSkill);

            //opening new activity for editing this new faaliat:
            Intent intent = new Intent(SkillsActivity.this, EditSkillActivity.class);
            intent.putExtra("The_Skill", DataHolder.Skills.size() - 1);
            startActivity(intent);
        }
    }

    public static void DeleteSkill(int skillIndex)
    {
        DataHolder.Skills.remove(skillIndex);
        skill_gridview_adapter.notifyDataSetChanged();
    }
}
