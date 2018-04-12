package com.farazannajmi.majesticlife;

import android.graphics.Bitmap;

import java.sql.Struct;
import java.util.List;

/**
 * Created by Narges on 3/28/2018.
 */

public class Activity_Majesty
{
    public String Name_ac;
    //public Bitmap Avatar;

    public int XpCount;
    public int HpCount;
    public int SpCount;

    public List<Skill_Time> SkillTimes;

    public Activity_Majesty (String Name_ac, /*Bitmap Avatar,*/ int XpCount, int HpCount, int SpCount, List<Skill_Time> SkillTimes)
    {
        this.Name_ac = Name_ac;
        //this.Avatar = Avatar;
        this.XpCount = XpCount;
        this.HpCount = HpCount;
        this.SpCount = SpCount;
        this.SkillTimes = SkillTimes;
    }
}
