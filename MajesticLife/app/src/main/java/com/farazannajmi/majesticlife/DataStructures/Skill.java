package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import javax.annotation.Nonnull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Narges on 3/28/2018.
 */

@Entity(tableName = "Skill_table",
        foreignKeys = @ForeignKey(entity = User.class,
            parentColumns = "User_ID",
            childColumns = "User_ID",
            onDelete = CASCADE))
public class Skill
{
    @Nonnull
    @PrimaryKey
    @ColumnInfo(name = "Skill_ID")
    private int Skill_ID;

    @ColumnInfo(name = "Skill_Name")
    private String Skill_Name;

    @ColumnInfo(name = "Avatar_ResIndex")
    private int Avatar_ResIndex;

    @ColumnInfo(name = "Level")
    private int Level;

    @ColumnInfo(name = "Progress")
    private int Progress; //the increasing value to reach the next level

    @ColumnInfo(name = "User_ID")
    private int User_ID;

    @Nonnull
    public int getSkill_ID(){return this.Skill_ID;}
    public String getSkill_Name(){return this.Skill_Name;}
    public int getAvatar_ResIndex(){return this.Avatar_ResIndex;}
    public int getLevel(){return this.Level;}
    public int getProgress(){return this.Progress;}
    public int getUser_ID(){return this.User_ID;}

    public void setSkill_ID(int Skill_ID){this.Skill_ID = Skill_ID;}
    public void setSkill_Name (String Skill_Name){this.Skill_Name = Skill_Name;}
    public void setAvatar_ResIndex (int Avatar_ResIndex){this.Avatar_ResIndex = Avatar_ResIndex;}
    public void setLevel (int Level){this.Level = Level;}
    public void setProgress (int Progress){this.Progress = Progress;}
    public void setUser_ID (int User_ID){this.User_ID = User_ID;}

    public Skill (String Skill_Name, int Avatar_ResIndex, int Level, int Progress, int User_ID)
    {
        this.Skill_Name = Skill_Name;
        this.Avatar_ResIndex = Avatar_ResIndex;
        this.Level = Level;
        this.Progress = Progress;
        this.User_ID = User_ID;
    }
}
