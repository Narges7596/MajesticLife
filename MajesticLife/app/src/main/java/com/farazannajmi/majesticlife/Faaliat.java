package com.farazannajmi.majesticlife;

import java.util.List;

/**
 * Created by Narges on 4/18/2018.
 */

public class Faaliat
{
    public String Name;
    //public Bitmap Avatar;

    public int XpCount;
    public int HpCount;
    public int SpCount;

    public List<Skill_Time> SkillTimes;

    public Faaliat (String Name, /*Bitmap Avatar,*/ int XpCount, int HpCount, int SpCount, List<Skill_Time> SkillTimes)
    {
        this.Name = Name;
        //this.Avatar = Avatar;
        this.XpCount = XpCount;
        this.HpCount = HpCount;
        this.SpCount = SpCount;
        this.SkillTimes = SkillTimes;
    }
}
