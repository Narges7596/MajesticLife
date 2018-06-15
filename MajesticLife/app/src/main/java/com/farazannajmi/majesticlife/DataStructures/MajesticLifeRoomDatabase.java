package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.farazannajmi.majesticlife.LoadingActivity;
import com.farazannajmi.majesticlife.R;

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
    private static final String DB_NAME = "MajesticLifeDatabase.db";
    private static volatile MajesticLifeRoomDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract FaaliatDao faaliatDao();
    public abstract SkillDao skillDao();
    public abstract QuestDao questDao();
    public abstract PlanCellDao planCellDao();
    public abstract FaaliatSkillDao faaliatSkillDao();
    public abstract QuestSkillDao questSkillDao();

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
                            MajesticLifeRoomDatabase.class, DB_NAME)
                            .addCallback(sRoomDatabaseCallback) //for initial data to database
                            .fallbackToDestructiveMigration() //drop and recreate the whole database if db version goes up
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static MajesticLifeRoomDatabase InitialDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (MajesticLifeRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MajesticLifeRoomDatabase.class, DB_NAME)
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
        private final UserDao userDao;
        private final FaaliatDao faaliatDao;
        private final SkillDao skillDao;
        private final QuestDao questDao;
        private final PlanCellDao planCellDao;
        private final FaaliatSkillDao faaliatSkillDao;
        private final QuestSkillDao questSkillDao;

        PopulateDbAsync(MajesticLifeRoomDatabase db)
        {
            userDao = db.userDao();
            faaliatDao = db.faaliatDao();
            skillDao = db.skillDao();
            questDao = db.questDao();
            planCellDao = db.planCellDao();
            faaliatSkillDao = db.faaliatSkillDao();
            questSkillDao = db.questSkillDao();
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            //User:
            userDao.deleteAll();
            User user = new User(0, "NewKing", "king@mail.com",
                    R.drawable.ic_king, 0, 1,0, 1, 0, 1);
            userDao.insert(user);

            faaliatDao.deleteAll();
            Faaliat faaliat = new Faaliat(0, "Reading book",
                    R.drawable.ic_majestic_activities, R.color.faaliatsColor1, 10,0,-1,0);
            faaliatDao.insert(faaliat);

            skillDao.deleteAll();
            Skill skill = new Skill("Knowledge", R.drawable.ic_skills, 1, 0, 0);
            skillDao.insert(skill);

            faaliatSkillDao.deleteAll();
            FaaliatSkill faaliatSkill = new FaaliatSkill(0, 0, 5);
            faaliatSkillDao.insert(faaliatSkill);

            planCellDao.deleteAll();
            PlanCell planCell = new PlanCell(0, 0, 0,5);
            planCellDao.insert(planCell);

            questDao.deleteAll();
            Quest quest = new Quest(0, "Pass exam", "20181203"); //todo: wrong DuaDate param
            questDao.insert(quest);

            questSkillDao.deleteAll();
            QuestSkill questSkill = new QuestSkill(0, 0, 2);
            questSkillDao.insert(questSkill);

            Log.d("Data", "Database Initialed!");
            LoadingActivity.LoadingDone = true;
            return null;
        }
    }
}
