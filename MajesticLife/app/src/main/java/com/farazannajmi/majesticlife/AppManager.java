package com.farazannajmi.majesticlife;

import android.app.Application;
import android.graphics.Bitmap;

import com.backtory.java.internal.BacktoryClient;
import com.backtory.java.internal.KeyConfiguration;
import com.backtory.java.internal.LogLevel;

import java.util.ArrayList;

/**
 * Created by Narges on 4/18/2018.
 */

public class AppManager extends Application
{
    public static ArrayList<Faaliat> Faaliats;
    public static ArrayList<Skill> Skills;
    public static User_Majesty User;

    public static ArrayList<Integer> FaaliatAvatars;

    @Override
    public void onCreate()
    {
        super.onCreate();

        // Logs with debug priority or higher. Includes network requests.
        // todo: when building for release, comment debug codes!!!!
        BacktoryClient.setDebugMode(BuildConfig.DEBUG);
        BacktoryClient.setLogLevel(LogLevel.Debug);

        // Initializing backtory
        BacktoryClient.init(KeyConfiguration.newBuilder().setAuthKeys(
                "5afd32ace4b02c90785846f2",
                "5afd32ace4b0322942dd05e7").
                // Finalizing sdk
                        build(), this);

        Faaliats = new ArrayList<Faaliat>();
        Skills = new ArrayList<Skill>();
        User = new User_Majesty();
        User.Avatar = R.drawable.ic_king;
        User.XP = 20;
        User.HP = 2;
        User.SP = 70;
        User.XpLevel = 3;
        User.HpLevel = 1;
        User.SpLevel = 10;

        FaaliatAvatars = new ArrayList<Integer>();
        FaaliatAvatars.add(R.drawable.ic_skills);
        FaaliatAvatars.add(R.drawable.ic_circle);
        FaaliatAvatars.add(R.drawable.ic_coin);
        FaaliatAvatars.add(R.drawable.ic_queen);
        FaaliatAvatars.add(R.drawable.ic_king);
        FaaliatAvatars.add(R.drawable.ic_quests);
        for (int i = 1; i < 10; i++)
        {
            FaaliatAvatars.add(R.drawable.ic_majestic_activities);
        }
    }
}
