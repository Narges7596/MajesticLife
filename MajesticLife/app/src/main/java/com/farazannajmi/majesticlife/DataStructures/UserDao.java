package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

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
}
