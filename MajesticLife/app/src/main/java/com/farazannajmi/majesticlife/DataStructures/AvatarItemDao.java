package com.farazannajmi.majesticlife.DataStructures;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Narges on 6/25/2018.
 */

@Dao
public interface AvatarItemDao
{
    @Insert
    void insert(AvatarItem avatarItem);

    @Update
    void update(AvatarItem avatarItem);

    @Delete
    void delete(AvatarItem avatarItem);

    @Query("DELETE FROM AvatarItem_table")
    void deleteAll();

    @Query("SELECT * FROM AvatarItem_table")
    LiveData<List<AvatarItem>> getAllAvatarItem();
}
