package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "Faaliat_table",
        foreignKeys = @ForeignKey(entity = User.class,
                parentColumns = "User_ID",
                childColumns = "User_ID",
                onDelete = CASCADE))
public class Faaliat
{
    //public ArrayList<FaaliatSkill> SkillTimes;

    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "Faaliat_ID")
    private int Faaliat_ID;

    @ColumnInfo(name = "Faaliat_Name")
    private String Faaliat_Name;

    @ColumnInfo(name = "Avatar_ResIndex")
    private int Avatar_ResIndex;
    @ColumnInfo(name = "Color_ResIndex")
    private int Color_ResIndex;

    @ColumnInfo(name = "XpCount")
    private int XpCount;
    @ColumnInfo(name = "HpCount")
    private int HpCount;
    @ColumnInfo(name = "SpCount")
    private int SpCount;

    @ColumnInfo(name = "User_ID")
    private int User_ID;

    @Nonnull
    public int getFaaliat_ID() {return Faaliat_ID;}
    public String getFaaliat_Name() {return Faaliat_Name;}
    public int getAvatar_ResIndex() {return Avatar_ResIndex;}
    public int getColor_ResIndex() {return Color_ResIndex;}
    public int getXpCount() {return XpCount;}
    public int getHpCount() {return HpCount;}
    public int getSpCount() {return SpCount;}
    public int getUser_ID() {return User_ID;}

    public void setFaaliat_ID(int Faaliat_ID){this.Faaliat_ID = Faaliat_ID;}
    public void setFaaliat_Name(String Faaliat_Name){this.Faaliat_Name = Faaliat_Name;}
    public void setAvatar_ResIndex(int Avatar_ResIndex){this.Avatar_ResIndex = Avatar_ResIndex;}
    public void setColor_ResIndex(int Color_ResIndex){this.Color_ResIndex = Color_ResIndex;}
    public void setXpCount(int XpCount){this.XpCount = XpCount;}
    public void setHpCount(int HpCount){this.HpCount = HpCount;}
    public void setSpCount(int SpCount){this.SpCount = SpCount;}
    public void setUser_ID(int User_ID){this.User_ID = User_ID;}

    public Faaliat(@Nonnull int Faaliat_ID, String Faaliat_Name, int Avatar_ResIndex, int Color_ResIndex,
                   int XpCount, int HpCount, int SpCount, int User_ID)
    {
        this.Faaliat_ID = Faaliat_ID;
        this.Faaliat_Name = Faaliat_Name;
        this.Avatar_ResIndex = Avatar_ResIndex;
        this.Color_ResIndex = Color_ResIndex;
        this.XpCount = XpCount;
        this.HpCount = HpCount;
        this.SpCount = SpCount;
        this.User_ID = User_ID;
    }
}
