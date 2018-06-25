package com.farazannajmi.majesticlife.DataStructures;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Created by Narges on 6/25/2018.
 */

@Dao
public interface AvatarDao
{
    @Insert
    void insert(Avatar avatar);

    @Update
    void update(Avatar avatar);

    @Delete
    void delete(Avatar avatar);

    @Query("DELETE FROM Avatar_table")
    void deleteAll();

    @Query("SELECT * FROM Avatar_table")
    LiveData<Avatar> getAvatar();
}
