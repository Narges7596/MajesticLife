package com.farazannajmi.majesticlife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.backtory.java.HttpStatusCode;
import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;

public class MainMenuActivity extends AppCompatActivity
{
    public AppManager TheAppManager;

    public ImageView UserAvatar_img;
    public ProgressBar XpLevel_progBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        TheAppManager = (AppManager) getApplicationContext();

        //getting UI elements
        UserAvatar_img = (ImageView) findViewById(R.id.Main_UserAvatar_img);
        XpLevel_progBar = (ProgressBar) findViewById(R.id.Main_XpLevel_progBar);

        //region handling login user
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        //if it is the first time app runs, the FirstTime value becomes true:
        boolean firstTime = sharedPref.getBoolean("FirstTime", true);

        if(firstTime) //if it's the first time opening the app and hasn't make a user in backtory
        {
            Log.d("WorkFlow", "First Time running app.");

            // Request a guest user from backtory:
            BacktoryUser.loginAsGuestInBackground(new BacktoryCallBack<Void>()
            {
                @Override
                public void onResponse(BacktoryResponse<Void> response)
                {
                    if(response.isSuccessful())
                    {
                        // Getting new username and password from CURRENT user
                        String newUsername = BacktoryUser.getCurrentUser().getUsername();
                        String newPassword = BacktoryUser.getCurrentUser().getGuestPassword();

                        Log.d("Backtory", "Logged in as guest, username: " + newUsername +
                                " password: " + newPassword);

                        //region saving
                        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();

                        editor.putBoolean("FirstTime", false);
                        editor.putString("UserName", newUsername);
                        editor.putString("UserPassword", newPassword);

                        editor.commit();
                        //endregion
                    }
                    else
                    {
                        Log.d("Backtory", "Failed log in as guest: " + response.code());
                    }
                }
            });
        }
        else //not the first time logging in
        {
            Log.d("WorkFlow", "Not the first Time running app.");

            // Fetch username/password from SharedPref
            String username = sharedPref.getString("UserName", "");
            String password = sharedPref.getString("UserPassword", "");

            // Pass user info to login
            BacktoryUser.loginInBackground(username, password,
                    new BacktoryCallBack<Void>()
                    {
                        @Override
                        public void onResponse(BacktoryResponse<Void> response)
                        {
                            // Checking result of operation
                            if (response.isSuccessful())
                            {
                                // Login successfull
                                Log.d("Backtory", "Logged in as username: " + BacktoryUser.getCurrentUser().getUsername());
                            }
                            else if (response.code() == HttpStatusCode.Unauthorized.code())
                            {
                                // Username 'mohx' with password '123456' is wrong
                                Log.d("Backtory", "Failed to log in: Either username or password is wrong");
                            }
                            else
                            {
                                // Operation generally failed, maybe internet connection issue
                                Log.d("Backtory", "Failed to log in: " + response.code());
                            }
                        }
                    });
        }
        //endregion
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
            case R.id.Main_Faaliat_btn:
            {
                Intent activitiesIntent = new Intent(MainMenuActivity.this, FaaliatsActivity.class);
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