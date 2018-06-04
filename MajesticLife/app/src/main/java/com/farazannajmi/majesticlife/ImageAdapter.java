package com.farazannajmi.majesticlife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Narges on 5/30/2018.
 */

public class ImageAdapter extends BaseAdapter
{
    private Context mContext;
    ArrayList<Integer> imagesList;
    LayoutInflater inflater;

    public ImageAdapter(Context context, ArrayList<Integer> imagesList)
    {
        mContext = context;
        this.imagesList = imagesList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount()
    {
        return imagesList.size();
    }

    public Object getItem(int position)
    {
        return imagesList.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listitem_avatar, null);
        }
        imageView = (ImageView) convertView.findViewById(R.id.listItem_avatar_img);

        imageView.setImageResource(imagesList.get(position));
        return imageView;
    }
}
