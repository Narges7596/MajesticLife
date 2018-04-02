package com.farazannajmi.majesticlife;

import android.content.Intent;
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
            case R.id.Main_UserAvatar_img:
            {
                Intent AccountIntent = new Intent(MainMenuActivity.this, AccountManagementActivity.class);
                startActivity(AccountIntent);
                break;
            }
            case R.id.Main_Skills_btn:
            {
                Intent skillsIntent = new Intent(MainMenuActivity.this, SkillsActivity.class);
                startActivity(skillsIntent);
                break;
            }
            case R.id.Main_DoActivity_btn:
            {
                Intent activitiesIntent = new Intent(MainMenuActivity.this, ActivitiesMajesticActivity.class);
                startActivity(activitiesIntent);
                break;
            }
            case R.id.Main_Plan_btn:
            {
                Intent planIntent = new Intent(MainMenuActivity.this, PlanActivity.class);
                startActivity(planIntent);
                break;
            }
            case R.id.Main_Quests_btn:
            {
                Intent questsIntent = new Intent(MainMenuActivity.this, QuestsActivity.class);
                startActivity(questsIntent);
                break;
            }
            default:
                break;
        }
    }
}