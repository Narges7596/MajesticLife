package com.farazannajmi.majesticlife;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Narges on 5/27/2018.
 */

public class EditFaaliatSkillsListItemArrayAdapter extends ArrayAdapter<Skill_Time>
{
    private Context context;
    private ArrayList<Skill_Time> skillTimesList;
    private AppManager TheAppManager;

    public EditFaaliatSkillsListItemArrayAdapter(@NonNull Context context, @NonNull ArrayList<Skill_Time> objects)
    {
        super(context, R.layout.listitem_faaliat, objects);

        this.context = context;
        this.skillTimesList = objects;
        TheAppManager = (AppManager) context;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //get the property we are displaying
        Skill_Time skillTime = getItem(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listitem_edit_faaliatskills, parent, false);

        //getting UI elements:
        Spinner skillsList_spinner = convertView.findViewById(R.id.listItem_editfaaliatskill_avatar_spinner);
        NumberPicker skillTimes_numberPicker = convertView.findViewById(R.id.listItem_editfaaliatskill_times_numberPicker);

        //setting info in UI:
        SkillImageSpinnerAdapter spinnerAdapter = new SkillImageSpinnerAdapter(context, TheAppManager.Skills);
        skillsList_spinner.setAdapter(spinnerAdapter);

        //https://www.codingdemos.com/android-custom-spinner-images-text/

        return convertView;
    }
}
