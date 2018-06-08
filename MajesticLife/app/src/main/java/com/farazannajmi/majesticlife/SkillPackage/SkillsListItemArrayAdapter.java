package com.farazannajmi.majesticlife.SkillPackage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.FaaliatPackage.FaaliatsActivity;
import com.farazannajmi.majesticlife.R;

import java.util.ArrayList;

/**
 * Created by Narges on 5/8/2018.
 */

public class SkillsListItemArrayAdapter extends ArrayAdapter<Skill>
{
    private Context context;
    private ArrayList<Skill> skillsList;

    public SkillsListItemArrayAdapter(@NonNull Context context, @NonNull ArrayList<Skill> objects)
    {
        super(context, R.layout.listitem_faaliat, objects);

        this.context = context;
        this.skillsList = objects;
    }

    //called when rendering the list
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //get the property we are displaying
        Skill skill = getItem(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listitem_skill, parent, false);

        TextView name_txt = (TextView) convertView.findViewById(R.id.listItem_skill_name_txt);
        TextView level_txt = (TextView) convertView.findViewById(R.id.listItem_skill_level_txt);
        ImageView avatar_img = (ImageView) convertView.findViewById(R.id.listItem_skill_avatar_img);
        ProgressBar progress_Bar = (ProgressBar) convertView.findViewById(R.id.listItem_skill_progressBar);
        Button edit_btn = (Button) convertView.findViewById(R.id.listItem_skill_edit_btn);
        Button graph_btn = (Button) convertView.findViewById(R.id.listItem_skill_graph_btn);
        Button delete_btn = (Button) convertView.findViewById(R.id.listItem_skill_delete_btn);

        //setting info in UI:
        name_txt.setText(skill.getSkill_Name());
        level_txt.setText(Integer.toString(skill.getLevel()));

        progress_Bar.setMax(100);
        progress_Bar.setProgress(skill.getProgress());
        ObjectAnimator animation = ObjectAnimator.ofInt(progress_Bar, "progress", 0, skill.getProgress()); // see this max value coming back here, we animate towards that value
        animation.setDuration(1000); // in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        //get the image associated with this property
        avatar_img.setImageResource(skill.getAvatar_ResIndex());

        edit_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //opening new activity for editing this faaliat:
                Intent intent = new Intent(getContext(), EditSkillActivity.class);
                intent.putExtra("The_Skill", position);
                context.startActivity(intent);
            }
        });

        graph_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //todo
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //show are you sure dialogue popup:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                // Add action buttons
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        SkillsActivity.DeleteSkill(position);
                        Toast.makeText(context, "Skill deleted successfully.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

                builder.setTitle("Are you sure you want to delete this skill?");

                builder.create();
                builder.show();
            }
        });

        return convertView;
    }
}