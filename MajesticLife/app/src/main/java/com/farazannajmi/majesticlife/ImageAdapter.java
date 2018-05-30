package com.farazannajmi.majesticlife;

import android.content.Context;
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

    public ImageAdapter(Context context, ArrayList<Integer> imagesList)
    {
        mContext = context;
        this.imagesList = imagesList;
    }

    public int getCount()
    {
        return imagesList.size();
    }

    public Object getItem(int position)
    {
        return null;
    }

    public long getItemId(int position)
    {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null)
        {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(imagesList.get(position));
        return imageView;
    }
}
