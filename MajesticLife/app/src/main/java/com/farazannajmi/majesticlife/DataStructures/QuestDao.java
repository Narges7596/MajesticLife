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
public interface QuestDao
{
    @Insert
    void insert(Quest quest);

    @Update
    void update(Quest quest);

    @Delete
    void delete(Quest quest);

    @Query("DELETE FROM Quest_table")
    void deleteAll();

    @Query("SELECT * FROM Quest_table ORDER BY Quest_ID")
    LiveData<List<Quest>> getAllQuests();
}
