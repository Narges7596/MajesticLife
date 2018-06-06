package com.farazannajmi.majesticlife.DataStructures;

import android.graphics.Bitmap;

import com.backtory.java.internal.BacktoryUser;
import com.farazannajmi.majesticlife.R;

/**
 * Created by Narges on 3/28/2018.
 */

public class User_Majesty
{
    public BacktoryUser CurrentBacktoryUser;
    public String Username;
    public String Password;

    public String Email;
    public int Avatar;

    public int XP;
    public int XpLevel;

    public int HP;
    public int HpLevel;

    public int SP;
    public int SpLevel;

    public User_Majesty(/*String username, String password, int avatar*/)
    {
//        this.Username = username;
//        this.Password = password;
//        this.Avatar = avatar;
        this.Avatar = R.drawable.ic_king;
    }
}
