package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Time;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Created by Narges on 3/28/2018.
 */

@Entity(tableName = "Quest_table")
public class Quest
{
    //public List<QuestSkill> QuestSkill;

    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "Quest_ID")
    private int Quest_ID;

    @ColumnInfo(name = "Quest_Name")
    private String Quest_Name;

    @ColumnInfo(name = "DuaDate")
    private String DuaDate;

    @Nonnull
    public int getQuest_ID() {return Quest_ID;}
    public String getQuest_Name() {return Quest_Name;}
    public String getDuaDate() {return DuaDate;}

    public void setQuest_ID(@Nonnull int quest_ID) {Quest_ID = quest_ID;}
    public void setQuest_Name(String quest_Name) {Quest_Name = quest_Name;}
    public void setDuaDate(String duaDate) {DuaDate = duaDate;}

    public Quest(@Nonnull int Quest_ID, String Quest_Name, String DuaDate)
    {
        this.Quest_ID = Quest_ID;
        this.Quest_Name = Quest_Name;
        this.DuaDate = DuaDate;
    }
}
