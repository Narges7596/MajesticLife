package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by Narges on 6/7/2018.
 * https://www.youtube.com/watch?v=9xdtVdO-XAA
 * https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0
 * https://android.jlelse.eu/android-architecture-components-room-introduction-4774dd72a1ae
 */

//@Database(entities = {Word.class}, version = 1)
@Database(entities = {User.class, Faaliat.class, Skill.class, Quest.class, PlanCell.class, FaaliatSkill.class, QuestSkill.class},
        version = 1)
public abstract class MajesticLifeRoomDatabase extends RoomDatabase
{
    public abstract UserDao userDao();
    public abstract FaaliatDao faaliatDao();
    public abstract SkillDao skillDao();
    public abstract QuestDao questDao();
    public abstract PlanCellDao planCellDao();
    public abstract FaaliatSkillDao faaliatSkillDao();
    public abstract QuestSkillDao questSkillDao();

    private static MajesticLifeRoomDatabase INSTANCE;

    public static MajesticLifeRoomDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (MajesticLifeRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MajesticLifeRoomDatabase.class, "MajesticLife_database")
                            .addCallback(sRoomDatabaseCallback) //for initial data to database
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback()
            {
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db)
                {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
    {
        private final PlanCellDao mDao;

        PopulateDbAsync(MajesticLifeRoomDatabase db)
        {
            mDao = db.planCellDao();
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            //todo
            mDao.deleteAll();
            PlanCell planCell = new PlanCell(0,0,0,0);
            mDao.insert(planCell);
//            word = new Word("World");
//            mDao.insert(word);
            return null;
        }
    }
}
