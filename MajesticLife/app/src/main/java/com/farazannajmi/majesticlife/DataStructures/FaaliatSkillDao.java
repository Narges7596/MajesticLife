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
public interface FaaliatSkillDao
{
    @Insert
    void insert(FaaliatSkill faaliatSkill);

    @Update
    void update(FaaliatSkill faaliatSkill);

    @Delete
    void delete(FaaliatSkill faaliatSkill);

    @Query("DELETE FROM FaaliatSkill_table")
    void deleteAll();

    @Query("SELECT * FROM FaaliatSkill_table ORDER BY Faaliat_ID, Skill_ID")
    LiveData<List<FaaliatSkill>> getAllFaaliatSkills();

    @Query("SELECT * FROM Skill_table INNER JOIN FaaliatSkill_table ON" +
            " Skill_table.Skill_ID = FaaliatSkill_table.Skill_ID " +
            "WHERE FaaliatSkill_table.Faaliat_ID=:faaliatID")
    LiveData<List<Skill>> getSkillsForFaaliat(final int faaliatID);
}
