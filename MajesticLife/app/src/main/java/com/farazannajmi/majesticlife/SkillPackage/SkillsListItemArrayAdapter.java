package com.farazannajmi.majesticlife.SkillPackage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.farazannajmi.majesticlife.DataStructures.Skill;
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

        //setting info in UI:
        name_txt.setText(skill.Name);
        level_txt.setText(Integer.toString(skill.Level));

        progress_Bar.setMax(100);
        progress_Bar.setProgress(skill.Progress);
        ObjectAnimator animation = ObjectAnimator.ofInt(progress_Bar, "progress", 0, skill.Progress); // see this max value coming back here, we animate towards that value
        animation.setDuration(1000); // in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        //get the image associated with this property
//        int imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName());
//        avatar_img.setImageResource(imageID);

        return convertView;
    }
}