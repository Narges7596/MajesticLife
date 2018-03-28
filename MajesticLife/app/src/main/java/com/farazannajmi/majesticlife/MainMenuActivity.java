package com.farazannajmi.majesticlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainMenuActivity extends AppCompatActivity
{

    public ImageView UserAvatar_img;
    public ProgressBar XpLevel_progBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        UserAvatar_img = (ImageView) findViewById(R.id.Main_UserAvatar_img);
        XpLevel_progBar = (ProgressBar) findViewById(R.id.Main_XpLevel_progBar);
    }

    public void UiElementsOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.Main_Skills_btn:
            {
                break;
            }
            case R.id.Main_DoActivity_btn:
            {
                break;
            }
            case R.id.Main_Plan_btn:
            {
                break;
            }
            case R.id.Main_Quests_btn:
            {
                break;
            }
            default:
                break;
        }
    }
}