package com.farazannajmi.majesticlife;

/**
 * Created by Narges on 4/12/2018.
 */

public class MajesticActivity_ListItem
{
    //property basics
    private String mActivityName;
    private int hp;
    private int sp;
    private int xp;
    private Skill_Time[] skills;
    //private ? avatar;

    //constructor
    public MajesticActivity_ListItem(String mActivityName, int hp, int sp, int xp, Skill_Time[] skills)
    {
        this.mActivityName = mActivityName;
        this.hp = hp;
        this.sp = sp;
        this.xp = xp;
        this.skills = skills;
    }

    //getters
    public String getMActivityName() {return mActivityName;}
    public int getHP() {return hp;}
    public int getSP() {return sp;}
    public int getXP() {return xp;}
    public Skill_Time[] getSkills() {return skills;}
    //public ? getAvater() {return avatar;}
}
