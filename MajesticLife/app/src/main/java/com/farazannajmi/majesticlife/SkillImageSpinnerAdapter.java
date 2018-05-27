package com.farazannajmi.majesticlife;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Narges on 5/27/2018.
 */

public class SkillImageSpinnerAdapter extends ArrayAdapter<String>
{
    String[] spinnerTitles;
    int[] spinnerImages;

    Context context;

    public SkillImageSpinnerAdapter(@NonNull Context context, ArrayList<Skill> skills)
    {
        super(context, R.layout.skillimage_spinner_row);

        this.spinnerTitles = new String[skills.size()];
        for(int i = 0; i < skills.size(); i ++)
            spinnerTitles[i] = skills.get(i).Name;

        this.spinnerImages = new int[skills.size()];
        for(int i = 0; i < skills.size(); i ++)
            spinnerImages[i] = skills.get(i).Avatar;

        this.context = context;
    }

    @Override
    public int getCount()
    {
        return spinnerTitles.length;
    }

    private static class ViewHolder
    {
        ImageView image;
        TextView title;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null)
        {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.skillimage_spinner_row, parent, false);
            mViewHolder.image = (ImageView) convertView.findViewById(R.id.skillspinner_img);
            mViewHolder.title = (TextView) convertView.findViewById(R.id.skillspinner_name_txt);
            convertView.setTag(mViewHolder);
        }
        else
        {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.image.setImageResource(spinnerImages[position]);
        mViewHolder.title.setText(spinnerTitles[position]);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        return getView(position, convertView, parent);
    }
}
