package com.farazannajmi.majesticlife.SkillPackage;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.DataStructures.SkillViewModel;
import com.farazannajmi.majesticlife.R;

public class SkillsActivity extends AppCompatActivity
{
    public static AppCompatActivity appCompatActivity;
    public static Context context;
    public static ArrayAdapter<Skill> skill_gridview_adapter;

    private GridView skills_gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        context = getApplicationContext();
        appCompatActivity = this;

        //getting UI elements
        skills_gridview = (GridView) findViewById(R.id.SkillsActivity_gridview);

        //create our new array adapter
        skill_gridview_adapter = new SkillsListItemArrayAdapter(this, DataHolder.Skills);
        skills_gridview.setAdapter(skill_gridview_adapter);
    }

    public void UiElementsOnClick(View view)
    {
        if (view.getId() == R.id.SkillsActivity_addSkill_btn)
        {
            Skill newSkill = new Skill(DataHolder.Skills.size(), "new skill", DataHolder.SkillAvatars.get(0),1,0, DataHolder.ThisUser.getUser_ID());
            DataHolder.Skills.add(newSkill);

            //adding skill in database:
            SkillViewModel skillViewModel = ViewModelProviders.of(this).get(SkillViewModel.class);
            skillViewModel.insert(newSkill);

            //opening new activity for editing this new faaliat:
            Intent intent = new Intent(SkillsActivity.this, EditSkillActivity.class);
            intent.putExtra("The_Skill", DataHolder.Skills.size() - 1);
            startActivity(intent);
        }
    }

    public static void DeleteSkill(int skillIndex)
    {
        //deleting skill from database:
        SkillViewModel skillViewModel = ViewModelProviders.of(appCompatActivity).get(SkillViewModel.class);
        skillViewModel.delete(DataHolder.Skills.get(skillIndex));

        DataHolder.Skills.remove(skillIndex);
        skill_gridview_adapter.notifyDataSetChanged();
    }
}
