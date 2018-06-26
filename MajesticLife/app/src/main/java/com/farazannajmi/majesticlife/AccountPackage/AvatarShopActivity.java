package com.farazannajmi.majesticlife.AccountPackage;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.AvatarItem;
import com.farazannajmi.majesticlife.DataStructures.AvatarViewModel;
import com.farazannajmi.majesticlife.DataStructures.FaaliatViewModel;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.DataStructures.UserViewModel;
import com.farazannajmi.majesticlife.R;

public class AvatarShopActivity extends AppCompatActivity
{
    public static Context context;
    public static AppCompatActivity appCompatActivity;
    public static ArrayAdapter<AvatarItem> avatarItems_gridview_adapter;

    private GridView avatarItems_gridview;
    public static ImageView main_back_img;
    public static ImageView main_skin_img;
    public static ImageView main_cloth_img;
    public static ImageView main_eyes_img;
    public static ImageView main_mouth_img;
    public static ImageView main_crown_img;
    public static ImageView main_hair_img;
    public static RadioButton king_radio;
    public static RadioButton queen_radio;
    public static TextView coins_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_shop);
        context = this;
        appCompatActivity = this;

        //region ---------- getting UI elements: ----------
        main_back_img = findViewById(R.id.AvatarShop_Back_img);
        main_skin_img = findViewById(R.id.AvatarShop_skin_img);
        main_cloth_img = findViewById(R.id.AvatarShop_cloth_img);
        main_eyes_img = findViewById(R.id.AvatarShop_eyes_img);
        main_mouth_img = findViewById(R.id.AvatarShop_mouth_img);
        main_crown_img = findViewById(R.id.AvatarShop_crown_img);
        main_hair_img = findViewById(R.id.AvatarShop_hair_img);
        avatarItems_gridview = findViewById(R.id.AvatarShop_gridview);
        king_radio = findViewById(R.id.AvatarShop_king_radio);
        queen_radio = findViewById(R.id.AvatarShop_queen_radio);
        coins_txt = findViewById(R.id.AvatarShop_Coins_txt);
        //endregion ------------------------------

        //region ---------- setting current avatar items ----------
        main_back_img.setImageResource(DataHolder.UserAvatar.getBackground_ResIndex());
        main_skin_img.setImageResource(DataHolder.UserAvatar.getSkin_ResIndex());
        main_cloth_img.setImageResource(DataHolder.UserAvatar.getCloth_ResIndex());
        main_eyes_img.setImageResource(DataHolder.UserAvatar.getEyes_ResIndex());
        main_mouth_img.setImageResource(DataHolder.UserAvatar.getMouth_ResIndex());
        main_crown_img.setImageResource(DataHolder.UserAvatar.getCrown_ResIndex());

        coins_txt.setText(Integer.toString((DataHolder.ThisUser.getCoins())));

        if(DataHolder.UserAvatar.getIsKing())
        {
            main_hair_img.setVisibility(View.INVISIBLE);
            king_radio.setChecked(true);
            queen_radio.setChecked(false);
        }
        else
        {
            main_hair_img.setVisibility(View.VISIBLE);
            king_radio.setChecked(false);
            queen_radio.setChecked(true);
        }
        //endregion ------------------------------

        //create new array adapter
        avatarItems_gridview_adapter = new AvatarShopListItemArrayAdapter(this, DataHolder.AvatarItems);
        avatarItems_gridview.setAdapter(avatarItems_gridview_adapter);
    }

    public void onRadioButtonClicked(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId())
        {
            case R.id.AvatarShop_king_radio:
            {
                if (checked)
                {
                    DataHolder.UserAvatar.setIsKing(true);
                    main_hair_img.setVisibility(View.INVISIBLE);
                }
                break;
            }
            case R.id.AvatarShop_queen_radio:
            {
                if (checked)
                {
                    DataHolder.UserAvatar.setIsKing(false);
                    main_hair_img.setVisibility(View.VISIBLE);
                }
                break;
            }
        }
    }

    public void UiElementsOnClick(View view)
    {
        if(view.getId() == R.id.AvatarShop_save_btn)
        {
            SavingChanges();
            finish();
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        SavingChanges();
    }

    public void SavingChanges()
    {
        //saving avatar
        AvatarViewModel avatarViewModel = ViewModelProviders.of(this).get(AvatarViewModel.class);
        avatarViewModel.update(DataHolder.UserAvatar);

        //saving user
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.update(DataHolder.ThisUser);
    }
}
