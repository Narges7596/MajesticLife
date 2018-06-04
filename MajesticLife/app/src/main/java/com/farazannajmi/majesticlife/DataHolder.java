package com.farazannajmi.majesticlife;

import java.util.ArrayList;

/**
 * Created by Narges on 5/30/2018.
 */

public class DataHolder
{
    public static ArrayList<Faaliat> Faaliats;
    public static ArrayList<Skill> Skills;
    public static User_Majesty User;

    public static ArrayList<Integer> FaaliatAvatars;
    public static ArrayList<Integer> SkillAvatars;

    public static void InitialData()
    {
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
}
