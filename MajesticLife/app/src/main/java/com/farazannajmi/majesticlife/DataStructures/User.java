package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.farazannajmi.majesticlife.R;

import javax.annotation.Nonnull;

/**
 * Created by Narges on 3/28/2018.
 */

@Entity(tableName = "User_table")
public class User
{
    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "User_ID")
    private int User_ID;

    @ColumnInfo(name = "Username")
    private String Username;

    @ColumnInfo(name = "Email")
    private String Email;

    @ColumnInfo(name = "Avatar_ResIndex")
    private int Avatar_ResIndex;

    @ColumnInfo(name = "XP")
    private int XP;
    @ColumnInfo(name = "XpLevel")
    private int XpLevel;

    @ColumnInfo(name = "HP")
    private int HP;
    @ColumnInfo(name = "HpLevel")
    private int HpLevel;

    @ColumnInfo(name = "SP")
    private int SP;
    @ColumnInfo(name = "SpLevel")
    private int SpLevel;

    @Nonnull
    public int getUser_ID(){return this.User_ID;}
    public String getUsername(){return this.Username;}
    public String getEmail(){return this.Email;}
    public int getAvatar_ResIndex(){return this.Avatar_ResIndex;}
    public int getXP(){return this.XP;}
    public int getXpLevel(){return this.XpLevel;}
    public int getHP(){return this.HP;}
    public int getHpLevel(){return this.HpLevel;}
    public int getSP(){return this.SP;}
    public int getSpLevel(){return this.SpLevel;}

    public void setUser_ID(int User_ID){this.User_ID = User_ID;}
    public void setUsername(String Username){this.Username = Username;}
    public void setEmail(String Email){this.Email = Email;}
    public void setAvatar_ResIndex(int Avatar_ResIndex){this.Avatar_ResIndex = Avatar_ResIndex;}
    public void setXP(int XP){this.XP = XP;}
    public void setXpLevel(int XpLevel){this.XpLevel = XpLevel;}
    public void setHP(int HP){this.HP = HP;}
    public void setHpLevel(int HpLevel){this.HpLevel = HpLevel;}
    public void setSP(int SP){this.SP = SP;}
    public void setSpLevel(int SpLevel){this.SpLevel = SpLevel;}

    public User(int User_ID, String Username, String Email, int Avatar_ResIndex,
                int XP, int XpLevel, int HP, int HpLevel, int SP, int SpLevel)
    {
        this.User_ID = User_ID;
        this.Username = Username;
        this.Email = Email;
        this.Avatar_ResIndex = Avatar_ResIndex;
        this.XP = XP;
        this.XpLevel = XpLevel;
        this.HP = HP;
        this.HpLevel = HpLevel;
        this.SP = SP;
        this.SpLevel = SpLevel;
    }
}
