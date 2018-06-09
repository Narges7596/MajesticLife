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
 * Dao for User entity
 */

@Dao
public interface UserDao
{
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM User_table")
    LiveData<User> getUser();
}
