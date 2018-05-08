package com.farazannajmi.majesticlife;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //get the property we are displaying
        Faaliat faaliat = getItem(position);

        //get the inflater and inflate the XML layout for each item
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listitem_faaliat, parent, false);

        TextView name_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_name_txt);
        TextView hp_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_hp_txt);
        TextView sp_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_sp_txt);
        TextView xp_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_xp_txt);
        TextView skills_txt = (TextView) convertView.findViewById(R.id.listItem_faaliat_skills_txt);
        ImageView avatar_img = (ImageView) convertView.findViewById(R.id.listItem_faaliat_avatar_img);

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

        return convertView;
    }
}
