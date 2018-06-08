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
public interface FaaliatDao
{
    @Insert
    void insert(Faaliat faaliat);

    @Update
    void update(Faaliat faaliat);

    @Delete
    void delete(Faaliat faaliat);

    @Query("DELETE FROM Faaliat_table")
    void deleteAll();

    @Query("SELECT * FROM Faaliat_table ORDER BY Faaliat_ID")
    LiveData<List<Faaliat>> getAllFaaliats();
}
