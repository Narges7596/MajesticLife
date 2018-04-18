package com.farazannajmi.majesticlife;

import android.app.Application;
import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Narges on 4/18/2018.
 */

public class AppManager extends Application
{
    public ArrayList<Faaliat> Faaliats;
    public ArrayList<Skill> Skills;
    public User_Majesty User;

    public AppManager ()
    {
        Faaliats = new ArrayList<Faaliat>();
        Skills = new ArrayList<Skill>();
        User = new User_Majesty();
    }
}
