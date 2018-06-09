package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class QuestSkillRepository
{
    private QuestSkillDao mQuestSkillDao;
    private LiveData<List<QuestSkill>> mAllQuestSkills;

    public QuestSkillRepository(Application application)
    {
        MajesticLifeRoomDatabase db = MajesticLifeRoomDatabase.getDatabase(application);
        mQuestSkillDao = db.questSkillDao();
        mAllQuestSkills = mQuestSkillDao.getAllQuestSkills();
    }

    //Room executes all queries on a separate thread.
    //Observed LiveData will notify the observer when the data has changed.
    LiveData<List<QuestSkill>> getAllQuestSkills()
    {
        return mAllQuestSkills;
    }

    /*a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
     Room ensures that you don't do any long-running operations on the main thread, blocking the UI.*/
    public void insert (QuestSkill questSkill)
    {
        new QuestSkillRepository.insertAsyncTask(mQuestSkillDao).execute(questSkill);
    }
    private static class insertAsyncTask extends AsyncTask<QuestSkill, Void, Void>
    {
        private QuestSkillDao mAsyncTaskDao;

        insertAsyncTask(QuestSkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final QuestSkill... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update (QuestSkill questSkill)
    {
        new QuestSkillRepository.updateAsyncTask(mQuestSkillDao).execute(questSkill);
    }
    private static class updateAsyncTask extends AsyncTask<QuestSkill, Void, Void>
    {
        private QuestSkillDao mAsyncTaskDao;

        updateAsyncTask(QuestSkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final QuestSkill... params)
        {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    public void delete (QuestSkill questSkill)
    {
        new QuestSkillRepository.deleteAsyncTask(mQuestSkillDao).execute(questSkill);
    }
    private static class deleteAsyncTask extends AsyncTask<QuestSkill, Void, Void>
    {
        private QuestSkillDao mAsyncTaskDao;

        deleteAsyncTask(QuestSkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final QuestSkill... params)
        {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void deleteAll ()
    {
        new QuestSkillRepository.deleteAllAsyncTask(mQuestSkillDao).execute();
    }
    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private QuestSkillDao mAsyncTaskDao;

        deleteAllAsyncTask(QuestSkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void getSkillsForQuest (Quest quest)
    {
        new QuestSkillRepository.getSkillsForQuestAllAsyncTask(mQuestSkillDao).execute(quest);
    }
    private static class getSkillsForQuestAllAsyncTask extends AsyncTask<Quest, Void, Void>
    {
        private QuestSkillDao mAsyncTaskDao;

        getSkillsForQuestAllAsyncTask(QuestSkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Quest... params)
        {
            mAsyncTaskDao.getSkillsForQuest(params[0].getQuest_ID());
            return null;
        }
    }
}
