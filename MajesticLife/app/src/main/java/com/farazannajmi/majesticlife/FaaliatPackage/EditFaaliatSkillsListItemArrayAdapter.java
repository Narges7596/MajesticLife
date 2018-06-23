package com.farazannajmi.majesticlife.FaaliatPackage;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.FaaliatSkill;
import com.farazannajmi.majesticlife.DataStructures.FaaliatSkillViewModel;
import com.farazannajmi.majesticlife.R;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Created by Narges on 5/27/2018.
 *  https://www.codingdemos.com/android-custom-spinner-images-text/
 */

public class EditFaaliatSkillsListItemArrayAdapter extends ArrayAdapter<FaaliatSkill>
{
    private Context context;
    private ArrayList<FaaliatSkill> faaliatSkillsList;

    public EditFaaliatSkillsListItemArrayAdapter(@NonNull Context context, @NonNull ArrayList<FaaliatSkill> objects)
    {
        super(context, R.layout.listitem_faaliat, objects);

        this.context = context;
        this.faaliatSkillsList = objects;
    }

    //called when rendering the list
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //get the property we are displaying
        FaaliatSkill faaliatSkill = getItem(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listitem_edit_faaliatskills, parent, false);

        //getting UI elements:
        final Spinner skillsList_spinner = convertView.findViewById(R.id.listItem_editfaaliatskill_avatar_spinner);
        NumberPicker skillTimes_numberPicker = convertView.findViewById(R.id.listItem_editfaaliatskill_times_numberPicker);
        Button remove_btn = convertView.findViewById(R.id.listItem_editfaaliatskill_remove_btn);

        //setting info in UI:
        final int minNumberPickerValue = 1;
        final int maxNumberPickerValue = 10;
        skillTimes_numberPicker.setMinValue(minNumberPickerValue);
        skillTimes_numberPicker.setMaxValue(maxNumberPickerValue);
        skillTimes_numberPicker.setValue(faaliatSkill.getRepetitionCount());
        skillTimes_numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
            {
                EditFaaliatActivity.thisFaaliatSkills.get(position).setRepetitionCount(newVal);
            }
        });

        SkillImageSpinnerAdapter spinnerAdapter = new SkillImageSpinnerAdapter(context, DataHolder.Skills);
        skillsList_spinner.setAdapter(spinnerAdapter);

        skillsList_spinner.setSelection(faaliatSkill.getSkill_ID());

        skillsList_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l)
            {
                if(EditFaaliatActivity.IsSkillDuplicated(i))
                {
//                    Toast.makeText(getContext(), "This skill is duplicated.", Toast.LENGTH_SHORT).show();
//                    skillsList_spinner.setSelection(position);
                }
                else
                {
                    EditFaaliatActivity.thisFaaliatSkills.get(position).setSkill_ID(DataHolder.Skills.get(i).getSkill_ID());
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
                //delete in database:
                FaaliatSkillViewModel faaliatSkillViewModel = ViewModelProviders.of(EditFaaliatActivity.appCompatActivity).get(FaaliatSkillViewModel.class);
                faaliatSkillViewModel.delete(EditFaaliatActivity.thisFaaliatSkills.get(position));

                EditFaaliatActivity.thisFaaliatSkills.remove(position);

                EditFaaliatActivity.Skills_ListView_adapter.notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
