package com.farazannajmi.majesticlife.FaaliatPackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.R;

import java.util.ArrayList;

/**
 * Created by Narges on 4/18/2018.
 */

public class FaaliatsListItemArrayAdapter extends ArrayAdapter<Faaliat>
{
    private Context context;
    private ArrayList<Faaliat> faaliatList;

    public FaaliatsListItemArrayAdapter(@NonNull Context context, @NonNull ArrayList<Faaliat> objects)
    {
        super(context, R.layout.listitem_faaliat, objects);

        this.context = context;
        this.faaliatList = objects;
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
        TextView skills_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_skills_txt);
        ImageView avatar_img = (ImageView) convertView.findViewById(R.id.listItem_faaliat_avatar_img);
        Button edit_btn = (Button) convertView.findViewById(R.id.listItem_faaliat_edit_btn);
        Button graph_btn = (Button) convertView.findViewById(R.id.listItem_faaliat_graph_btn);
        Button pluse_btn = (Button) convertView.findViewById(R.id.listItem_faaliat_pluse_btn);

        //setting UI values
        name_txt.setText(faaliat.Name);
        layout.setBackgroundColor(ContextCompat.getColor(context, faaliat.fColor));
        hp_txt.setText(Integer.toString(faaliat.HpCount));
        sp_txt.setText(Integer.toString(faaliat.SpCount));
        xp_txt.setText(Integer.toString(faaliat.XpCount));

        String sk_txt = "";
        for (int i = 0; i < faaliat.SkillTimes.size(); i++)
        {
            sk_txt += "+" + faaliat.SkillTimes.get(i).RepeatingTime + " " + faaliat.SkillTimes.get(i).TheSill.Name + "\n";
        }

        skills_txt.setText(sk_txt);

        //get the image associated with this property
        avatar_img.setImageResource(faaliat.Avatar);

        edit_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //opening new activity for editing this faaliat:
                Intent intent = new Intent(getContext(), EditOneFaaliatPopupActivity.class);
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

        return convertView;
    }
}
