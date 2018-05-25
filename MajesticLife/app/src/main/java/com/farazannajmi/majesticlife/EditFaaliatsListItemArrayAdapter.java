package com.farazannajmi.majesticlife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Narges on 5/25/2018.
 */

public class EditFaaliatsListItemArrayAdapter extends ArrayAdapter<Faaliat>
{
    private Context context;
    private ArrayList<Faaliat> faaliatList;

    public EditFaaliatsListItemArrayAdapter(@NonNull Context context, @NonNull ArrayList<Faaliat> objects)
    {
        super(context, R.layout.listitem_faaliat, objects);

        this.context = context;
        this.faaliatList = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //get the property we are displaying
        Faaliat faaliat = getItem(position);

        //get the inflater and inflate the XML layout for each item
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listitem_edit_faaliat, parent, false);

        TextView name_txt = (TextView) convertView.findViewById(R.id.listItem_editfaaliat_name_txt);
        TextView hp_txt = (TextView) convertView.findViewById(R.id.listItem_editfaaliat_hp_txt);
        TextView sp_txt = (TextView) convertView.findViewById(R.id.listItem_editfaaliat_sp_txt);
        TextView xp_txt = (TextView) convertView.findViewById(R.id.listItem_editfaaliat_xp_txt);
        TextView skills_txt = (TextView) convertView.findViewById(R.id.listItem_editfaaliat_skills_txt);
        ImageView avatar_img = (ImageView) convertView.findViewById(R.id.listItem_editfaaliat_avatar_img);
        Button edit_btn = (Button) convertView.findViewById(R.id.listItem_editfaaliat_edit_btn);

        //setting UI values
        name_txt.setText(faaliat.Name);
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
//        int imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName());
//        image.setImageResource(imageID);

        setOnClick(edit_btn, position);

        return convertView;
    }

    private void setOnClick(final Button btn, final int position)
    {
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(getContext(), "clicked, name: " + faaliat.Name, Toast.LENGTH_LONG).show();

                //opening new activity for editing this faaliat:
                Intent intent = new Intent(getContext(), EditOneFaaliatPopupActivity.class);
                intent.putExtra("The_Faaliat", position);
                context.startActivity(intent);
            }
        });
    }
}