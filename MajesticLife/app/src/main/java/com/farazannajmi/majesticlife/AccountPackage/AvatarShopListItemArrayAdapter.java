package com.farazannajmi.majesticlife.AccountPackage;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.AvatarItem;
import com.farazannajmi.majesticlife.DataStructures.AvatarItemViewModel;
import com.farazannajmi.majesticlife.DataStructures.AvatarViewModel;
import com.farazannajmi.majesticlife.R;

import java.util.ArrayList;

/**
 * Created by Narges on 6/26/2018.
 */

public class AvatarShopListItemArrayAdapter extends ArrayAdapter<AvatarItem>
{
    private Context context;
    private ArrayList<AvatarItem> avatarItemsList;

    public AvatarShopListItemArrayAdapter(@NonNull Context context, @NonNull ArrayList<AvatarItem> objects)
    {
        super(context, R.layout.listitem_avatarshop, objects);

        this.context = context;
        this.avatarItemsList = objects;
    }

    //called when rendering the list
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //get the property we are displaying
        final AvatarItem avatarItem = getItem(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listitem_avatarshop, parent, false);

        ImageView avatar_img = (ImageView) convertView.findViewById(R.id.listItem_avaShop_img);
        TextView price_txt = (TextView) convertView.findViewById(R.id.listItem_avaShop_price_txt);
        final Button buy_btn = (Button) convertView.findViewById(R.id.listItem_avaShop_btn);

        //setting info in UI:
        price_txt.setText("x " + avatarItem.getPrice());
        avatar_img.setImageResource(avatarItem.getResourceIndex());
        if(avatarItem.getIsBought())
            buy_btn.setText("Select");
        else
            buy_btn.setText("Buy");


        buy_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(avatarItem.getIsBought())
                {
                    SelectItem(avatarItem);
                }
                else
                {
                    if(DataHolder.ThisUser.getCoins() >= avatarItem.getPrice())
                    {
                        buy_btn.setText("Select");
                        DataHolder.ThisUser.setCoins(DataHolder.ThisUser.getCoins() - avatarItem.getPrice());
                        AvatarShopActivity.coins_txt.setText(Integer.toString((DataHolder.ThisUser.getCoins())));
                        avatarItem.setIsBought(true);
                        SelectItem(avatarItem);

                        AvatarItemViewModel avatarItemViewModel = ViewModelProviders.of(AvatarShopActivity.appCompatActivity).get(AvatarItemViewModel.class);
                        avatarItemViewModel.update(avatarItem);
                    }
                    else
                        Toast.makeText(context, "Not enough coins!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    private void SelectItem(AvatarItem avatarItem)
    {
        switch (avatarItem.getItemType())
        {
            case 0: //background
            {
                AvatarShopActivity.main_back_img.setImageResource(avatarItem.getResourceIndex());
                DataHolder.UserAvatar.setBackground_ResIndex(avatarItem.getResourceIndex());
                break;
            }
            case 1: //skin
            {
                AvatarShopActivity.main_skin_img.setImageResource(avatarItem.getResourceIndex());
                DataHolder.UserAvatar.setSkin_ResIndex(avatarItem.getResourceIndex());
                break;
            }
            case 2: //cloth
            {
                AvatarShopActivity.main_cloth_img.setImageResource(avatarItem.getResourceIndex());
                DataHolder.UserAvatar.setCloth_ResIndex(avatarItem.getResourceIndex());
                break;
            }
            case 3: //eyes
            {
                AvatarShopActivity.main_eyes_img.setImageResource(avatarItem.getResourceIndex());
                DataHolder.UserAvatar.setEyes_ResIndex(avatarItem.getResourceIndex());
                break;
            }
            case 4: //mouth
            {
                AvatarShopActivity.main_mouth_img.setImageResource(avatarItem.getResourceIndex());
                DataHolder.UserAvatar.setMouth_ResIndex(avatarItem.getResourceIndex());
                break;
            }
            case 5: //crown
            {
                AvatarShopActivity.main_crown_img.setImageResource(avatarItem.getResourceIndex());
                DataHolder.UserAvatar.setCrown_ResIndex(avatarItem.getResourceIndex());
                break;
            }
            default:
                break;
        }
    }
}