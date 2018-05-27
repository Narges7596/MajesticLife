package com.farazannajmi.majesticlife;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import javax.xml.xpath.XPathConstants;


public class Faaliat
{
    public String Name;
    //public Bitmap Avatar;

    public int XpCount;
    public int HpCount;
    public int SpCount;

    public ArrayList<Skill_Time> SkillTimes;

    public Faaliat (String Name, /*Bitmap Avatar,*/ int XpCount, int HpCount, int SpCount, ArrayList<Skill_Time> SkillTimes)
    {
        this.Name = Name;
        //this.Avatar = Avatar;
        this.XpCount = XpCount;
        this.HpCount = HpCount;
        this.SpCount = SpCount;
        this.SkillTimes = SkillTimes;
    }
}
