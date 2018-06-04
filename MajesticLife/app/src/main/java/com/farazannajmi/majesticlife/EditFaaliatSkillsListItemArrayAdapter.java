package com.farazannajmi.majesticlife;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Narges on 5/27/2018.
 *  https://www.codingdemos.com/android-custom-spinner-images-text/
 */

public class EditFaaliatSkillsListItemArrayAdapter extends ArrayAdapter<Skill_Time>
{
    private Context context;
    private ArrayList<Skill_Time> skillTimesList;

    public EditFaaliatSkillsListItemArrayAdapter(@NonNull Context context, @NonNull ArrayList<Skill_Time> objects)
    {
        super(context, R.layout.listitem_faaliat, objects);

        this.context = context;
        this.skillTimesList = objects;
    }

    //called when rendering the list
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //get the property we are displaying
        Skill_Time skillTime = getItem(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listitem_edit_faaliatskills, parent, false);

        //getting UI elements:
        final Spinner skillsList_spinner = convertView.findViewById(R.id.listItem_editfaaliatskill_avatar_spinner);
        NumberPicker skillTimes_numberPicker = convertView.findViewById(R.id.listItem_editfaaliatskill_times_numberPicker);
        Button remove_btn = convertView.findViewById(R.id.listItem_editfaaliatskill_remove_btn);

        //setting info in UI:
        final int minNumberPickerValue = 0;
        final int maxNumberPickerValue = 10;
        skillTimes_numberPicker.setMinValue(minNumberPickerValue);
        skillTimes_numberPicker.setMaxValue(maxNumberPickerValue);
        skillTimes_numberPicker.setValue(skillTime.RepeatingTime);
        skillTimes_numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                EditOneFaaliatPopupActivity.thisFaaliat.SkillTimes.get(position).RepeatingTime = newVal;
            }
        });

        SkillImageSpinnerAdapter spinnerAdapter = new SkillImageSpinnerAdapter(context, DataHolder.Skills);
        skillsList_spinner.setAdapter(spinnerAdapter);

        skillsList_spinner.setSelection(skillTime.index);

        skillsList_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l)
            {
                if(EditOneFaaliatPopupActivity.IsSkillDuplicated(i))
                {
                    Toast.makeText(getContext(), "This skill is duplicated.", Toast.LENGTH_SHORT).show();
                    skillsList_spinner.setSelection(position);
                }
                else
                {
                    EditOneFaaliatPopupActivity.thisFaaliat.SkillTimes.get(position).TheSill = DataHolder.Skills.get(i);
                    EditOneFaaliatPopupActivity.thisFaaliat.SkillTimes.get(position).index = i;
                }
            }

            @Override
            public void onNothingSelected(AdapterView adapterView)
            {

            }
        });

        remove_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditOneFaaliatPopupActivity.thisFaaliat.SkillTimes.remove(position);
                EditOneFaaliatPopupActivity.Skills_ListView_adapter.notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
