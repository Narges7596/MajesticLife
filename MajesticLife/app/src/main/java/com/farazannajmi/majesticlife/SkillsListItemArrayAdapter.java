package com.farazannajmi.majesticlife;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    public View getView(int position, View convertView, ViewGroup parent)
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

        if(progress_Bar.isIndeterminate())
            progress_Bar.setIndeterminate(false);

        //set address and description
        name_txt.setText(skill.Name);
        level_txt.setText(Integer.toString(skill.Level));

        progress_Bar.setMax(100);
        progress_Bar.setProgress(skill.Progress);

        //get the image associated with this property
//        int imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName());
//        avatar_img.setImageResource(imageID);

        return convertView;
    }
}