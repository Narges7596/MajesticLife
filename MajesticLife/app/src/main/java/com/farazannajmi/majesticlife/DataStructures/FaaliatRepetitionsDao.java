package com.farazannajmi.majesticlife.DataStructures;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Narges on 6/20/2018.
 */

@Dao
public interface FaaliatRepetitionsDao
{
    @Insert
    void insert(FaaliatRepetitions faaliatRepetitions);

    @Update
    void update(FaaliatRepetitions faaliatRepetitions);

    @Delete
    void delete(FaaliatRepetitions faaliatRepetitions);

    @Query("DELETE FROM FaaliatRepetitions_table")
    void deleteAll();

    @Query("SELECT * FROM FaaliatRepetitions_table")
    LiveData<List<FaaliatRepetitions>> getAllFaaliatRepetitions();

    @Query("SELECT * FROM FaaliatRepetitions_table " +
            "WHERE FaaliatRepetitions_table.Faaliat_ID=:faaliatID " +
            "ORDER BY DayOfWeek")
    LiveData<List<FaaliatRepetitions>> getAllFaaliatRepsForFaaliat(final int faaliatID);

    @Query("SELECT * FROM FaaliatRepetitions_table " +
            "WHERE Faaliat_ID=:faaliatID AND FR_Date=:date " +
            "ORDER BY DayOfWeek")
    LiveData<List<FaaliatRepetitions>> getFaaliatRepsForFaaliatDate(final int faaliatID, final String date);
}
