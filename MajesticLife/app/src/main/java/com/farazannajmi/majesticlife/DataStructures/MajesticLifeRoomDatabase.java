package com.farazannajmi.majesticlife.DataStructures;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.farazannajmi.majesticlife.DataHolder;
import com.farazannajmi.majesticlife.LoadingActivity;
import com.farazannajmi.majesticlife.R;

/**
 * Created by Narges on 6/7/2018.
 * https://www.youtube.com/watch?v=9xdtVdO-XAA
 * https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0
 * https://android.jlelse.eu/android-architecture-components-room-introduction-4774dd72a1ae
 * https://medium.com/@srinuraop/database-create-and-open-callbacks-in-room-7ca98c3286ab
 */

@Database(entities = {User.class, Faaliat.class, Skill.class, Quest.class, PlanCell.class,
        FaaliatSkill.class, QuestSkill.class, FaaliatRepetitions.class,
        Avatar.class, AvatarItem.class},
        version = 5)
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
    public abstract FaaliatRepetitionsDao faaliatRepetitionsDao();
    public abstract AvatarDao avatarDao();
    public abstract AvatarItemDao avatarItemDao();

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
                else
                    LoadingActivity.isDataLoaded = true;
            }
        }
        else
            LoadingActivity.isDataLoaded = true;
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback()
            {
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db)
                {
                    super.onOpen(db);
                    if(!LoadingActivity.isFirstTime)
                    {
                        Log.d("Data", "Database has been initialed!");
                    }
                    LoadingActivity.isDataLoaded = true;

                    Log.d("Data", "Database opened!");
                }

                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db)
                {
                    super.onCreate(db);
                    new PopulateDbAsync(INSTANCE).execute();
                    Log.d("Data", "Database created!");
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
        private final FaaliatRepetitionsDao faaliatRepetitionsDao;
        private final AvatarDao avatarDao;
        private final AvatarItemDao avatarItemDao;

        PopulateDbAsync(MajesticLifeRoomDatabase db)
        {
            userDao = db.userDao();
            faaliatDao = db.faaliatDao();
            skillDao = db.skillDao();
            questDao = db.questDao();
            planCellDao = db.planCellDao();
            faaliatSkillDao = db.faaliatSkillDao();
            questSkillDao = db.questSkillDao();
            faaliatRepetitionsDao = db.faaliatRepetitionsDao();
            avatarDao = db.avatarDao();
            avatarItemDao = db.avatarItemDao();
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            if(LoadingActivity.isFirstTime && !DataHolder.IsDatabaseCreated)
            {
                //order is important!!

                //User:
                userDao.deleteAll();
                User user = new User(0, "NewKing", "king@mail.com",
                        R.drawable.ic_king, 0, 1,0, 1, 0, 1, 100);
                userDao.insert(user);

                //Faaliats:
                faaliatDao.deleteAll();
                Faaliat faaliat = new Faaliat(0, "Reading book",
                        R.drawable.ic_majestic_activities, R.color.faaliatsColor1, 10,0,1,0);
                faaliatDao.insert(faaliat);

                //Skills:
                skillDao.deleteAll();
                Skill skill = new Skill(0, "Knowledge", R.drawable.ic_skills, 1, 0, 0);
                skillDao.insert(skill);

                //FaaliatSkills:
                faaliatSkillDao.deleteAll();
                FaaliatSkill faaliatSkill = new FaaliatSkill(0, 0, 5);
                faaliatSkillDao.insert(faaliatSkill);

                //PlanCells:
                planCellDao.deleteAll();
                PlanCell planCell = new PlanCell(0, 0, 0,5);
                planCellDao.insert(planCell);

                //Quests:
                questDao.deleteAll();
                Quest quest = new Quest(0, "Pass exam", "2019/1/1"); //todo: wrong DuaDate param
                questDao.insert(quest);

                //QuestSkills:
                questSkillDao.deleteAll();
                QuestSkill questSkill = new QuestSkill(0, 0, 2);
                questSkillDao.insert(questSkill);

                //region ---------- avatarItems ----------
                //backgrounds:
                avatarItemDao.deleteAll();
                AvatarItem ava_back1 = new AvatarItem(R.drawable.ava_back1, true, 0, 0);
                avatarItemDao.insert(ava_back1);
                AvatarItem ava_back2 = new AvatarItem(R.drawable.ava_back2, false, 0, 50);
                avatarItemDao.insert(ava_back2);
                AvatarItem ava_back3 = new AvatarItem(R.drawable.ava_back3, false, 0, 60);
                avatarItemDao.insert(ava_back3);
                AvatarItem ava_back4 = new AvatarItem(R.drawable.ava_back4, false, 0, 60);
                avatarItemDao.insert(ava_back4);
                AvatarItem ava_back5 = new AvatarItem(R.drawable.ava_back5, false, 0, 70);
                avatarItemDao.insert(ava_back5);
                AvatarItem ava_back6 = new AvatarItem(R.drawable.ava_back6, false, 0, 70);
                avatarItemDao.insert(ava_back6);
                AvatarItem ava_back7 = new AvatarItem(R.drawable.ava_back7, false, 0, 70);
                avatarItemDao.insert(ava_back7);

                //Skins
                AvatarItem ava_skin1 = new AvatarItem(R.drawable.ava_skin_white, true, 0, 0);
                avatarItemDao.insert(ava_skin1);
                //todo: insert other clothes

                //Clothes
                AvatarItem ava_cloth1 = new AvatarItem(R.drawable.ava_cloth1, true, 0, 0);
                avatarItemDao.insert(ava_cloth1);
                //todo: insert other clothes

                //Eyes
                AvatarItem ava_eyes1 = new AvatarItem(R.drawable.ava_eyes1, true, 0, 0);
                avatarItemDao.insert(ava_eyes1);
                //todo: insert other eyes

                //Mouths
                AvatarItem ava_mouth1 = new AvatarItem(R.drawable.ava_mouth1, true, 0, 0);
                avatarItemDao.insert(ava_mouth1);
                //todo: insert other mouths

                //Crowns
                AvatarItem ava_crown1 = new AvatarItem(R.drawable.ava_crown1, true, 0, 0);
                avatarItemDao.insert(ava_crown1);
                //todo: insert other crowns
                //endregion ------------------------------

                //no initial FaaliatRepetitions
                //todo test:
                faaliatRepetitionsDao.deleteAll();
                FaaliatRepetitions fr1 = new FaaliatRepetitions(0, 1, "2018/6/10", 1);
                FaaliatRepetitions fr2 = new FaaliatRepetitions(0, 2, "2018/6/11", 3);
                FaaliatRepetitions fr3 = new FaaliatRepetitions(0, 3, "2018/6/12", 2);
                FaaliatRepetitions fr4 = new FaaliatRepetitions(0, 4, "2018/6/13", 5);
                FaaliatRepetitions fr5 = new FaaliatRepetitions(0, 6, "2018/6/15", 3);
                FaaliatRepetitions fr6 = new FaaliatRepetitions(0, 1, "2018/6/17", 1);
                FaaliatRepetitions fr7 = new FaaliatRepetitions(0, 3, "2018/6/19", 1);
                FaaliatRepetitions fr8 = new FaaliatRepetitions(0, 4, "2018/6/20", 2);
                faaliatRepetitionsDao.insert(fr1);
                faaliatRepetitionsDao.insert(fr2);
                faaliatRepetitionsDao.insert(fr3);
                faaliatRepetitionsDao.insert(fr4);
                faaliatRepetitionsDao.insert(fr5);
                faaliatRepetitionsDao.insert(fr6);
                faaliatRepetitionsDao.insert(fr7);
                faaliatRepetitionsDao.insert(fr8);

                //Avatar:
                avatarDao.deleteAll();
                Avatar avatar = new Avatar(0,0, true, R.drawable.ava_back1,
                        R.drawable.ava_skin_white, R.drawable.ava_cloth1,
                        R.drawable.ava_eyes1, R.drawable.ava_mouth1, R.drawable.ava_crown1);
                avatarDao.insert(avatar);

                DataHolder.IsDatabaseCreated = true;
                Log.d("Data", "Database initialed!");
            }
            return null;
        }
    }
}
