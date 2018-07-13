package com.farazannajmi.majesticlife;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;
import com.farazannajmi.majesticlife.AccountPackage.AccountManagementActivity;
import com.farazannajmi.majesticlife.DataStructures.UserViewModel;
import com.farazannajmi.majesticlife.FaaliatPackage.FaaliatsActivity;
import com.farazannajmi.majesticlife.PlanPackage.PlanActivity;
import com.farazannajmi.majesticlife.QuestPackage.QuestsActivity;
import com.farazannajmi.majesticlife.SkillPackage.SkillsActivity;

public class MainMenuActivity extends AppCompatActivity
{
    public static AppManager TheAppManager;

    public TextView Username_txt;
    public TextView Coins_txt;

    public TextView XpLevel_txt;
    public TextView HpLevel_txt;
    public TextView SpLevel_txt;
    public ProgressBar Xp_progBar;
    public ProgressBar Hp_progBar;
    public ProgressBar Sp_progBar;

    public ImageView Avatar_back_img;
    public ImageView Avatar_skin_img;
    public ImageView Avatar_cloth_img;
    public ImageView Avatar_eyes_img;
    public ImageView Avatar_mouth_img;
    public ImageView Avatar_crown_img;
    public ImageView Avatar_hair_img;

    public static boolean FirstTime;
    private boolean _isConnected;
    public static boolean GuestNotLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final AppCompatActivity activity = this;

        TheAppManager = (AppManager) getApplicationContext();

        //region ----------- getting UI elements -----------
        Username_txt = (TextView) findViewById(R.id.Main_UserName_txt);
        Coins_txt = (TextView) findViewById(R.id.Main_Coins_txt);
        XpLevel_txt = findViewById(R.id.Main_XpLevel_txt);
        HpLevel_txt = findViewById(R.id.Main_HpLevel_txt);
        SpLevel_txt = findViewById(R.id.Main_SpLevel_txt);
        Xp_progBar = findViewById(R.id.Main_Xp_progBar);
        Hp_progBar = findViewById(R.id.Main_HP_progBar);
        Sp_progBar = findViewById(R.id.Main_SP_progBar);

        Avatar_back_img = findViewById(R.id.MainM_Back_img);
        Avatar_skin_img = findViewById(R.id.MainM_skin_img);
        Avatar_cloth_img = findViewById(R.id.MainM_cloth_img);
        Avatar_eyes_img = findViewById(R.id.MainM_eyes_img);
        Avatar_mouth_img = findViewById(R.id.MainM_mouth_img);
        Avatar_crown_img = findViewById(R.id.MainM_crown_img);
        Avatar_hair_img = findViewById(R.id.MainM_hair_img);
        //endregion ---------------------------------

        //for checking internet connection:
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        _isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        //region ----------- load or initial data -----------

        DataHolder.LoadData(this, this);
        DataHolder.LoadFaaliatSkills(this, this);

        //if it is the first time app runs, the FirstTime value becomes true:
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        FirstTime = sharedPref.getBoolean("FirstTime", true);
        GuestNotLoggedIn = sharedPref.getBoolean("GuestNotLoggedIn", true);

        if(FirstTime)
        {
            //region first time
            Log.d("WorkFlow", "First Time running app.");

            if(_isConnected)
            {
                Toast.makeText(getApplicationContext(), "Connected to the Internet!", Toast.LENGTH_SHORT).show();

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

                            // saving user info in sharedPreference:
                            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putBoolean("GuestNotLoggedIn", false);
                            editor.putString("UserName", newUsername);
                            editor.putString("UserPassword", newPassword);
                            editor.commit();

                            //saving username in database:
                            DataHolder.ThisUser.setUsername(newUsername);
                            UserViewModel userViewModel = ViewModelProviders.of(activity).get(UserViewModel.class);
                            userViewModel.update(DataHolder.ThisUser);

                            DataHolder.CurrentBacktoryUser = BacktoryUser.getCurrentUser();
                        } else {
                            Log.d("Backtory", "Failed log in as guest: " + response.code());
                        }
                    }
                });
            }
            else //if is not connected to internet
            {
                //todo
                Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("GuestNotLoggedIn", true);
                editor.putString("UserName", "NewKing");
                editor.putString("UserPassword", "1111");
                editor.commit();
            }

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("FirstTime", false);
            editor.commit();
            //endregion
        }
        else //not the first time opening the application
        {
            //region not first time
            Log.d("WorkFlow", "Not the first Time running app.");

            if(_isConnected)
            {
                if(GuestNotLoggedIn)
                {
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

                                // saving user info in sharedPreference:
                                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putBoolean("GuestNotLoggedIn", false);
                                editor.putString("UserName", newUsername);
                                editor.putString("UserPassword", newPassword);
                                editor.commit();

                                //saving username in database:
                                DataHolder.ThisUser.setUsername(newUsername);
                                UserViewModel userViewModel = ViewModelProviders.of(activity).get(UserViewModel.class);
                                userViewModel.update(DataHolder.ThisUser);

                                DataHolder.CurrentBacktoryUser = BacktoryUser.getCurrentUser();
                            } else {
                                Log.d("Backtory", "Failed log in as guest: " + response.code());
                            }
                        }
                    });
                }
                else //guest has been logged in
                {
                    // loading username and password from SharedPref
                    String username = sharedPref.getString("UserName", "");
                    String password = sharedPref.getString("UserPassword", "");

                    Log.d("WorkFlow", "Username: " + username);

//                    // Pass user info to login
//                    BacktoryUser.loginInBackground(username, password,
//                            new BacktoryCallBack<Void>()
//                        {
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
            }
            else //if not connected to internet
            {
                //todo
                Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_SHORT).show();

                // loading username and password from SharedPref
                String username = sharedPref.getString("UserName", "");
                String password = sharedPref.getString("UserPassword", "");
                Log.d("WorkFlow", "Username: " + username);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("GuestNotLoggedIn", true);
                editor.commit();
            }

            DataHolder.LoadData(this, this);
            //endregion
        }
        //endregion ---------------------------------

        InitialUIElements();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        InitialUIElements();
    }

    @Override
    public void onBackPressed()
    {
        try
        {
            finishAffinity();
        }
        catch (Exception e)
        {
            ActivityCompat.finishAffinity(MainMenuActivity.this);
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
        Username_txt.setText(DataHolder.ThisUser.getUsername());
        Coins_txt.setText(Integer.toString(DataHolder.ThisUser.getCoins()));

        XpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getXpLevel()));
        HpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getHpLevel()));
        SpLevel_txt.setText(Integer.toString(DataHolder.ThisUser.getSpLevel()));
        Xp_progBar.setProgress(DataHolder.ThisUser.getXP());
        Hp_progBar.setProgress(DataHolder.ThisUser.getHP());
        Sp_progBar.setProgress(DataHolder.ThisUser.getSP());

        //setting user avatar:
        Avatar_back_img.setImageResource(DataHolder.UserAvatar.getBackground_ResIndex());
        Avatar_skin_img.setImageResource(DataHolder.UserAvatar.getSkin_ResIndex());
        Avatar_cloth_img.setImageResource(DataHolder.UserAvatar.getCloth_ResIndex());
        Avatar_eyes_img.setImageResource(DataHolder.UserAvatar.getEyes_ResIndex());
        Avatar_mouth_img.setImageResource(DataHolder.UserAvatar.getMouth_ResIndex());
        Avatar_crown_img.setImageResource(DataHolder.UserAvatar.getCrown_ResIndex());
        if(DataHolder.UserAvatar.getIsKing())
        {
            Avatar_hair_img.setVisibility(View.INVISIBLE);
        }
        else
        {
            Avatar_hair_img.setVisibility(View.VISIBLE);
        }
    }
}