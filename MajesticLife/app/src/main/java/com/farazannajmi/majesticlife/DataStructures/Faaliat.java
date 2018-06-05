package com.farazannajmi.majesticlife.DataStructures;

import java.util.ArrayList;


public class Faaliat
{
    public String Name;
    public int Avatar;
    public int fColor;

    public int XpCount;
    public int HpCount;
    public int SpCount;

    public ArrayList<Skill_Time> SkillTimes;

    public Faaliat (String Name, int Avatar, int color, int XpCount, int HpCount, int SpCount, ArrayList<Skill_Time> SkillTimes)
    {
        this.Name = Name;
        this.Avatar = Avatar;
        this.fColor = color;
        this.XpCount = XpCount;
        this.HpCount = HpCount;
        this.SpCount = SpCount;
        this.SkillTimes = SkillTimes;
    }
}
