package com.farazannajmi.majesticlife.FaaliatPackage;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.DataStructures.FaaliatSkill;
import com.farazannajmi.majesticlife.DataStructures.FaaliatSkillViewModel;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.R;
import com.farazannajmi.majesticlife.SkillPackage.SkillsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Created by Narges on 4/18/2018.
 */

public class FaaliatsListItemArrayAdapter extends ArrayAdapter<Faaliat>
{
    private Context context;
    private Activity activity;
    private ArrayList<Faaliat> faaliatList;
    //static ArrayList<FaaliatSkill> faaliatSkills;
    static String sk_txt;

    public FaaliatsListItemArrayAdapter(@NonNull Context context, @NonNull ArrayList<Faaliat> objects, @Nonnull Activity activity)
    {
        super(context, R.layout.listitem_faaliat, objects);

        this.context = context;
        this.faaliatList = objects;
        this.activity = activity;
    }

    //called when rendering the list
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //get the property we are displaying
        Faaliat faaliat = getItem(position);

        //get the inflater and inflate the XML layout for each item
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listitem_faaliat, parent, false);

        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.listItem_faaliat_layoutparent);
        TextView name_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_name_txt);
        TextView hp_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_hp_txt);
        TextView sp_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_sp_txt);
        TextView xp_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_xp_txt);
        //TextView skills_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_skills_txt);
        ImageView avatar_img = (ImageView) convertView.findViewById(R.id.listItem_faaliat_avatar_img);
        Button edit_btn = (Button) convertView.findViewById(R.id.listItem_faaliat_edit_btn);
        Button graph_btn = (Button) convertView.findViewById(R.id.listItem_faaliat_graph_btn);
        Button pluse_btn = (Button) convertView.findViewById(R.id.listItem_faaliat_pluse_btn);
        Button delete_btn = (Button) convertView.findViewById(R.id.listItem_faaliat_delete_btn);

        //setting UI values
        name_txt.setText(faaliat.getFaaliat_Name());
        layout.setBackgroundColor(ContextCompat.getColor(context, faaliat.getColor_ResIndex()));
        hp_txt.setText(Integer.toString(faaliat.getHpCount()));
        sp_txt.setText(Integer.toString(faaliat.getSpCount()));
        xp_txt.setText(Integer.toString(faaliat.getXpCount()));


        //getting FaaliatSkills and setting it in UI:
        final View finalConvertView = convertView;
        FaaliatSkillViewModel faaliatSkillViewModel = ViewModelProviders.of((FragmentActivity) activity).get(FaaliatSkillViewModel.class);
        faaliatSkillViewModel.getSkillsForFaaliat(faaliat).observe((LifecycleOwner) context, new Observer<List<FaaliatSkill>>() {
            @Override
            public void onChanged(@Nullable List<FaaliatSkill> skills) {
                ArrayList<FaaliatSkill> faaliatSkills = (ArrayList) skills;

                String sk_txt = "";
                for (int i = 0; i < faaliatSkills.size(); i++)
                {
                    sk_txt += "+" + faaliatSkills.get(i).getRepetitionCount() + " " +
                            DataHolder.Skills.get(faaliatSkills.get(i).getSkill_ID()).getSkill_Name() + "\n";
                }

                TextView skills_txt = (TextView) finalConvertView.findViewById(R.id.listItem_faaliat_skills_txt);
                skills_txt.setText(sk_txt);
            }
        });

        //get the image associated with this property
        avatar_img.setImageResource(faaliat.getAvatar_ResIndex());

        edit_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //opening new activity for editing this faaliat:
                Intent intent = new Intent(getContext(), EditFaaliatActivity.class);
                intent.putExtra("The_Faaliat", position);
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

        pluse_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("WorkFlow", "Clicked on pluse faaliat btn.");

                //showing dialogue popup to get how many hours user has done this faaliat
                PluseFaaliatDialogFragment pluseFaaliatDialog = PluseFaaliatDialogFragment.newInstance(position);
                pluseFaaliatDialog.show(((Activity) context).getFragmentManager(), "PluseFaaliatDialogFragment");
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
                        FaaliatsActivity.DeleteFaaliat(position);
                        Toast.makeText(context, "Activity deleted successfully.", Toast.LENGTH_SHORT).show();
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

                builder.setTitle("Are you sure you want to delete this activity?");

                builder.create();
                builder.show();
            }
        });
        return convertView;
    }
}
