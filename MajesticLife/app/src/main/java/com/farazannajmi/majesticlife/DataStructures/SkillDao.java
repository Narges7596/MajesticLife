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
public interface SkillDao
{
    @Insert
    void insert(Skill skill);

    @Update
    void update(Skill skill);

    @Delete
    void delete(Skill skill);

    @Query("DELETE FROM Skill_table")
    void deleteAll();

    @Query("SELECT * FROM Skill_table ORDER BY Skill_ID")
    LiveData<List<Skill>> getAllSkills();

//    @Query("SELECT * FROM Skill_table WHERE User_ID=:userId")
//    List<Skill> findSkillsForUser(final int userId);
}
