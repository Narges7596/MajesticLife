package com.farazannajmi.majesticlife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AccountManagementActivity extends AppCompatActivity
{
    public AppManager TheAppManager;

    public ImageView Avatar_img;
    public TextView Username_txt;
    public TextView XpLevel_txt;
    public TextView HpLevel_txt;
    public TextView SpLevel_txt;
    public ProgressBar XpLevel_progBar;
    public ProgressBar HpLevel_progBar;
    public ProgressBar SpLevel_progBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        TheAppManager = (AppManager) getApplicationContext();

        Avatar_img = findViewById(R.id.AccountM_Avatar_img);
        Username_txt = findViewById(R.id.AccountM_Username_txt);
        XpLevel_txt = findViewById(R.id.AccountM_XpLevel_txt);
        HpLevel_txt = findViewById(R.id.AccountM_HpLevel_txt);
        SpLevel_txt = findViewById(R.id.AccountM_SpLevel_txt);
        XpLevel_progBar = findViewById(R.id.AccountM_XpLevel_progBar);
        HpLevel_progBar = findViewById(R.id.AccountM_HP_progBar);
        SpLevel_progBar = findViewById(R.id.AccountM_SP_progBar);

        //region setting right UI values!

        //Avatar_img.setImageBitmap(TheAppManager.User.Avatar);
        Username_txt.setText(TheAppManager.User.CurrentBacktoryUser.getUsername());
        XpLevel_txt.setText(TheAppManager.User.XpLevel);
        HpLevel_txt.setText(TheAppManager.User.HpLevel);
        SpLevel_txt.setText(TheAppManager.User.SpLevel);
        XpLevel_progBar.setProgress(TheAppManager.User.XP);
        HpLevel_progBar.setProgress(TheAppManager.User.HP);
        SpLevel_progBar.setProgress(TheAppManager.User.SP);

        //endregion
    }

    public void UiElementsOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.AccountM_Signup_btn:
            {
                break;
            }
            case R.id.AccountM_Login_btn:
            {
                break;
            }
            default:
                break;
        }
    }
}
