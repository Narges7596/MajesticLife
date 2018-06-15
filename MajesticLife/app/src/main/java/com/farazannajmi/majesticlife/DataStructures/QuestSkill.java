package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.farazannajmi.majesticlife.DataStructures.Faaliat;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Narges on 4/18/2018.
 * join entity table of Quest and Skill
 */

@Entity(tableName = "QuestSkill_table",
        primaryKeys = { "Quest_ID", "Skill_ID" },
        foreignKeys = {
                @ForeignKey(entity = Quest.class,
                        parentColumns = "Quest_ID",
                        childColumns = "Quest_ID",
                        onDelete = CASCADE),
                @ForeignKey(entity = Skill.class,
                        parentColumns = "Skill_ID",
                        childColumns = "Skill_ID",
                        onDelete = CASCADE)
        })
public class QuestSkill
{
    private int Quest_ID;
    private int Skill_ID;

    @ColumnInfo(name = "TargetLevel")
    private int TargetLevel;

    public int getQuest_ID() {return Quest_ID;}
    public int getSkill_ID() {return Skill_ID;}
    public int getTargetLevel() {return TargetLevel;}

    public void setQuest_ID(int quest_ID) {Quest_ID = quest_ID;}
    public void setSkill_ID(int skill_ID) {Skill_ID = skill_ID;}
    public void setTargetLevel(int targetLevel) {TargetLevel = targetLevel;}

    public QuestSkill(int Quest_ID, int Skill_ID, int TargetLevel)
    {
        this.Quest_ID = Quest_ID;
        this.Skill_ID = Skill_ID;
        this.TargetLevel = TargetLevel;
    }
}
