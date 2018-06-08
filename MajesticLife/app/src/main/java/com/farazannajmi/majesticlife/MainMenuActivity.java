package com.farazannajmi.majesticlife;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;
import com.farazannajmi.majesticlife.AccountPackage.AccountManagementActivity;
import com.farazannajmi.majesticlife.FaaliatPackage.FaaliatsActivity;
import com.farazannajmi.majesticlife.PlanPackage.PlanActivity;
import com.farazannajmi.majesticlife.QuestPackage.QuestsActivity;
import com.farazannajmi.majesticlife.SkillPackage.SkillsActivity;

public class MainMenuActivity extends AppCompatActivity
{
    public static AppManager TheAppManager;

    public ImageView UserAvatar_img;
    public TextView Username_txt;

    public TextView XpLevel_txt;
    public TextView HpLevel_txt;
    public TextView SpLevel_txt;
    public ProgressBar Xp_progBar;
    public ProgressBar Hp_progBar;
    public ProgressBar Sp_progBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        TheAppManager = (AppManager) getApplicationContext();

        //region ----------- getting UI elements -----------
        UserAvatar_img = (ImageView) findViewById(R.id.Main_UserAvatar_img);
        Username_txt = (TextView) findViewById(R.id.Main_UserName_txt);
        XpLevel_txt = findViewById(R.id.Main_XpLevel_txt);
        HpLevel_txt = findViewById(R.id.Main_HpLevel_txt);
        SpLevel_txt = findViewById(R.id.Main_SpLevel_txt);
        Xp_progBar = findViewById(R.id.Main_Xp_progBar);
        Hp_progBar = findViewById(R.id.Main_HP_progBar);
        Sp_progBar = findViewById(R.id.Main_SP_progBar);
        //endregion ---------------------------------

        //checking internet connection:
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected)
        {
            Toast.makeText(getApplicationContext(), "Connected to the Internet!", Toast.LENGTH_SHORT).show();

            //region handling login user
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

            //if it is the first time app runs, the FirstTime value becomes true:
            boolean firstTime = sharedPref.getBoolean("FirstTime", true);

            if (firstTime) //if it's the first time opening the app and hasn't make a user in backtory
            {
                Log.d("WorkFlow", "First Time running app.");

                DataHolder.InitialData();

                // Request a guest user from backtory:
                BacktoryUser.loginAsGuestInBackground(new BacktoryCallBack<Void>() {
                    @Override
                    public void onResponse(BacktoryResponse<Void> response) {
                        if (response.isSuccessful()) {
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

                            DataHolder.CurrentBacktoryUser = BacktoryUser.getCurrentUser();

                            InitialUIElements();

                            editor.commit();
                            //endregion
                        } else {
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


                DataHolder.LoadData();

                //todo
                //DataHolder.User.CurrentBacktoryUser = BacktoryUser.getCurrentUser();
                DataHolder.User.setUsername(username);
                //DataHolder.User.Password = password;

                InitialUIElements();

                //todo
                //Log.d("WorkFlow", "Username: " + DataHolder.User.CurrentBacktoryUser.getUsername());

//            // Pass user info to login
//            BacktoryUser.loginInBackground(username, password,
//                    new BacktoryCallBack<Void>()
//                    {
//                        @Override
//                        public void onResponse(BacktoryResponse<Void> response)
//                        {
//                            // Checking result of operation
//                            if (response.isSuccessful())
//                            {
//                                // Login successfull
//                                Log.d("Backtory", "Logged in as username: " + BacktoryUser.getCurrentUser().getUsername());
//                            }
//                            else if (response.code() == HttpStatusCode.Unauthorized.code())
//                            {
//                                // Username 'mohx' with password '123456' is wrong
//                                Log.d("Backtory", "Failed to log in: Either username or password is wrong");
//                            }
//                            else
//                            {
//                                // Operation generally failed, maybe internet connection issue
//                                Log.d("Backtory", "Failed to log in: " + response.code());
//                            }
//                        }
//                    });
            }
            //endregion
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_SHORT).show();
        }
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
            case R.id.Main_Shop_btn:
            {
                //todo
                break;
            }
            default:
                break;
        }
    }

    public void InitialUIElements()
    {
        Username_txt.setText(DataHolder.User.getUsername());
        UserAvatar_img.setImageResource(DataHolder.User.getAvatar_ResIndex());

        XpLevel_txt.setText(Integer.toString(DataHolder.User.getXpLevel()));
        HpLevel_txt.setText(Integer.toString(DataHolder.User.getHpLevel()));
        SpLevel_txt.setText(Integer.toString(DataHolder.User.getSpLevel()));
        Xp_progBar.setProgress(DataHolder.User.getXP());
        Hp_progBar.setProgress(DataHolder.User.getHP());
        Sp_progBar.setProgress(DataHolder.User.getSP());
    }
}