package com.farazannajmi.majesticlife;

import android.graphics.Bitmap;

import com.backtory.java.internal.BacktoryUser;

/**
 * Created by Narges on 3/28/2018.
 */

public class User_Majesty
{
    public BacktoryUser CurrentBacktoryUser;

    public String Email;
    public int Avatar;

    public int XP;
    public int XpLevel;

    public int HP;
    public int HpLevel;

    public int SP;
    public int SpLevel;

    public User_Majesty()
    {
        this.Avatar = R.drawable.ic_king;
    }
}
