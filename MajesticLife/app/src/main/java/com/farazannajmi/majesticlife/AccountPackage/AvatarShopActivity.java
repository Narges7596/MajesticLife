package com.farazannajmi.majesticlife.AccountPackage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.R;

public class AvatarShopActivity extends AppCompatActivity
{
    public static Context context;
    public static ArrayAdapter<Skill> avatarItems_gridview_adapter;

    private GridView avatarItems_gridview;
    private ImageView main_back_img;
    private ImageView main_skin_img;
    private ImageView main_cloth_img;
    private ImageView main_eyes_img;
    private ImageView main_mouth_img;
    private ImageView main_crown_img;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_shop);

        //region ---------- getting UI elements: ----------
        main_back_img = findViewById(R.id.AvatarShop_Back_img);
        main_skin_img = findViewById(R.id.AvatarShop_skin_img);
        main_cloth_img = findViewById(R.id.AvatarShop_cloth_img);
        main_eyes_img = findViewById(R.id.AvatarShop_eyes_img);
        main_mouth_img = findViewById(R.id.AvatarShop_mouth_img);
        main_crown_img = findViewById(R.id.AvatarShop_crown_img);
        avatarItems_gridview = findViewById(R.id.AvatarShop_gridview);
        //endregion ------------------------------

        //region ---------- setting current avatar items ----------
        main_back_img.setImageResource(DataHolder.UserAvatar.getBackground_ResIndex());
        main_skin_img.setImageResource(DataHolder.UserAvatar.getSkin_ResIndex());
        main_cloth_img.setImageResource(DataHolder.UserAvatar.getCloth_ResIndex());
        main_eyes_img.setImageResource(DataHolder.UserAvatar.getEyes_ResIndex());
        main_mouth_img.setImageResource(DataHolder.UserAvatar.getMouth_ResIndex());
        main_crown_img.setImageResource(DataHolder.UserAvatar.getCrown_ResIndex());
        //endregion ------------------------------

        //create new array adapter
        //avatarItems_gridview_adapter = new SkillsListItemArrayAdapter(this, DataHolder.AvatarItems); //todo
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
                    //todo
                }
                break;
            }
            case R.id.AvatarShop_queen_radio:
            {
                if (checked)
                {
                    //todo
                }
                break;
            }
        }


        if(view.getId() == R.id.AvatarShop_king_radio)
        {

        }
        else if(view.getId() == R.id.AvatarShop_queen_radio)
        {

        }
    }

    public void UiElementsOnClick(View view)
    {
        if(view.getId() == R.id.AvatarShop_save_btn)
        {

        }
    }
}
