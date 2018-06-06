package com.farazannajmi.majesticlife.DataStructures;

import android.graphics.Bitmap;

/**
 * Created by Narges on 3/28/2018.
 */

public class Skill
{
    public String Name;
    public int Avatar;
    public int Level;
    /**
     * the increasing value to reach the next level
     */
    public int Progress;

    public Skill (String Name, int Avatar, int Level, int Progress)
    {
        this.Name = Name;
        this.Avatar = Avatar;
        this.Level = Level;
        this.Progress = Progress;
    }
}