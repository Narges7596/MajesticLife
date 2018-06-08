package com.farazannajmi.majesticlife.DataStructures;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Narges on 6/8/2018.
 */

@Dao
public interface QuestSkillDao
{
    @Insert
    void insert(QuestSkill questSkill);

    @Update
    void update(QuestSkill questSkill);

    @Delete
    void delete(QuestSkill questSkill);

    @Query("DELETE FROM QuestSkill_table")
    void deleteAll();

    @Query("SELECT * FROM QuestSkill_table ORDER BY Quest_ID, Skill_ID")
    LiveData<List<QuestSkill>> getAllQuestSkills();

    @Query("SELECT * FROM Skill_table INNER JOIN QuestSkill_table ON" +
            " Skill_table.Skill_ID = QuestSkill_table.Skill_ID " +
            "WHERE QuestSkill_table.Quest_ID=:questID")
    List<Skill> getSkillsForQuest(final int questID);
}
