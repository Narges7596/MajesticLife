package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.farazannajmi.majesticlife.DataStructures.Skill;

/**
 * Created by Narges on 3/28/2018.
 * join entity table of Faaliat and Skill
 */

@Entity(tableName = "FaaliatSkill_table",
        primaryKeys = { "Faaliat_ID", "Skill_ID" },
        foreignKeys = {
                @ForeignKey(entity = Faaliat.class,
                        parentColumns = "Faaliat_ID",
                        childColumns = "Faaliat_ID"),
                @ForeignKey(entity = Skill.class,
                        parentColumns = "Skill_ID",
                        childColumns = "Skill_ID")
        })
public class FaaliatSkill
{
    private int Faaliat_ID;
    private int Skill_ID;

    @ColumnInfo(name = "RepetitionCount")
    private int RepetitionCount;

    @ColumnInfo(name = "ListIndex")
    private int ListIndex;

    public int getFaaliat_ID() {return Faaliat_ID;}
    public int getSkill_ID() {return Skill_ID;}
    public int getRepetitionCount() {return RepetitionCount;}
    public int getListIndex() {return ListIndex;}

    public void setFaaliat_ID(int faaliat_ID) {Faaliat_ID = faaliat_ID;}
    public void setSkill_ID(int skill_ID) {Skill_ID = skill_ID;}
    public void setRepetitionCount(int repetitionCount) {RepetitionCount = repetitionCount;}
    public void setListIndex(int listIndex) {ListIndex = listIndex;}

    public FaaliatSkill(int Faaliat_ID, int Skill_ID, int RepetitionCount, int ListIndex)
    {
        this.Faaliat_ID = Faaliat_ID;
        this.Skill_ID = Skill_ID;
        this.RepetitionCount = RepetitionCount;
        this.ListIndex = ListIndex;
    }

//    public Skill TheSill;
//    public int RepeatingTime;
//    public int index;

//    public FaaliatSkill(Skill TheSill, int RepeatingTime, int index)
//    {
//        this.TheSill = TheSill;
//        this.RepeatingTime = RepeatingTime;
//        this.index = index;
//    }
}
