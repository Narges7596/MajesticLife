package com.farazannajmi.majesticlife;

import android.database.sqlite.SQLiteDatabase;

import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.DataStructures.Skill_Time;
import com.farazannajmi.majesticlife.DataStructures.User_Majesty;

import java.util.ArrayList;

/**
 * Created by Narges on 5/30/2018.
 */

public class DataHolder
{
    public static User_Majesty User;
    public static ArrayList<Faaliat> Faaliats;
    public static ArrayList<Skill> Skills;

    public static Faaliat[][] WeeklyPlan;

    public static ArrayList<Integer> FaaliatAvatars;
    public static ArrayList<Integer> SkillAvatars;

    private static SQLiteDatabase database;

    public static void InitialData()
    {
        //todo: remove tests and initial all user xp & ... with 0
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

        WeeklyPlan = new Faaliat[17][7];
        for(int dayCounter = 0; dayCounter < 7; dayCounter++)
        {
            for(int hourCounter = 0; hourCounter < 17; hourCounter++)
            {
                WeeklyPlan[hourCounter][dayCounter] =
                        new Faaliat("empty", 0,0,0,0,0,null);
            }
        }

        FaaliatAvatars = new ArrayList<Integer>();
        FaaliatAvatars.add(R.drawable.ic_majestic_activities);
        FaaliatAvatars.add(R.drawable.ic_circle);
        FaaliatAvatars.add(R.drawable.ic_coin);
        FaaliatAvatars.add(R.drawable.ic_queen);
        FaaliatAvatars.add(R.drawable.ic_king);
        FaaliatAvatars.add(R.drawable.ic_quests);
        for (int i = 1; i < 10; i++)
        {
            FaaliatAvatars.add(R.drawable.ic_majestic_activities);
        }

        SkillAvatars = new ArrayList<Integer>();
        SkillAvatars.add(R.drawable.ic_skills);
        SkillAvatars.add(R.drawable.ic_circle);
        SkillAvatars.add(R.drawable.ic_coin);
        SkillAvatars.add(R.drawable.ic_queen);
        SkillAvatars.add(R.drawable.ic_king);
        SkillAvatars.add(R.drawable.ic_quests);
        for (int i = 1; i < 10; i++)
        {
            FaaliatAvatars.add(R.drawable.ic_skills);
        }

        //region ----------------------------test------------------------------
        Skill s1 = new Skill("Knowledge", SkillAvatars.get(0), 5, 50);
        DataHolder.Skills.add(s1);
        Skill s2 = new Skill("HandCrafting", SkillAvatars.get(1), 10, 25);
        DataHolder.Skills.add(s2);
        Skill s3 = new Skill("Elm", SkillAvatars.get(2), 5, 50);
        DataHolder.Skills.add(s3);
        Skill s4 = new Skill("Olom", SkillAvatars.get(3), 2, 25);
        DataHolder.Skills.add(s4);


        ArrayList<Skill_Time> st = new ArrayList<Skill_Time>();
        st.add(new Skill_Time(DataHolder.Skills.get(0), 10, 0));
        st.add(new Skill_Time(DataHolder.Skills.get(1), 20, 1));
        DataHolder.Faaliats.add(new Faaliat("Embroidery", FaaliatAvatars.get(0),
                R.color.faaliatsColor1, -10, 5, 2, st));

        ArrayList<Skill_Time> st2 = new ArrayList<Skill_Time>();
        st2.add(new Skill_Time(DataHolder.Skills.get(2), 2, 2));
        st2.add(new Skill_Time(DataHolder.Skills.get(3), 3, 3));
        DataHolder.Faaliats.add(new Faaliat("Alaki", FaaliatAvatars.get(0),
                R.color.faaliatsColor3, -1, 2, 3, st2));
        //endregion
    }

    public static void LoadData()
    {
        //todo
        InitialData();
    }

    public static void SaveData ()
    {
        //todo
    }

//    private static boolean InitDataBase ()
//    {
//        try
//        {
//            database = SQLiteDatabase.openOrCreateDatabase("MajesticLife_DB", null);
//        }
//        catch (Exception e)
//        {
//            return false;
//        }
//
//        return true;
//    }
}
