package com.farazannajmi.majesticlife.DataStructures;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Narges on 6/7/2018.
 */

@Dao
public interface PlanCellDao
{
    @Insert
    void insert(PlanCell planCell);

    @Update
    void update(PlanCell planCell);

    @Delete
    void delete(PlanCell planCell);

    @Query("DELETE FROM PlanCell_table")
    void deleteAll();

    @Query("SELECT * FROM PlanCell_table ORDER BY WeekDay, DayHour")
    LiveData<List<PlanCell>> getAllPlanCells();
}
