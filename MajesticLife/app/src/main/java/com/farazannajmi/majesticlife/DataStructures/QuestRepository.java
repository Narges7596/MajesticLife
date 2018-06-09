package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class QuestRepository
{
    private QuestDao mQuestDao;
    private LiveData<List<Quest>> mAllQuests;

    public QuestRepository(Application application)
    {
        MajesticLifeRoomDatabase db = MajesticLifeRoomDatabase.getDatabase(application);
        mQuestDao = db.questDao();
        mAllQuests = mQuestDao.getAllQuests();
    }

    LiveData<List<Quest>> getAllQuests()
    {
        return mAllQuests;
    }

    public void insert (Quest quest)
    {
        new QuestRepository.insertAsyncTask(mQuestDao).execute(quest);
    }
    private static class insertAsyncTask extends AsyncTask<Quest, Void, Void>
    {
        private QuestDao mAsyncTaskDao;

        insertAsyncTask(QuestDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Quest... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update (Quest quest)
    {
        new QuestRepository.updateAsyncTask(mQuestDao).execute(quest);
    }
    private static class updateAsyncTask extends AsyncTask<Quest, Void, Void>
    {
        private QuestDao mAsyncTaskDao;

        updateAsyncTask(QuestDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Quest... params)
        {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    public void delete (Quest quest)
    {
        new QuestRepository.deleteAsyncTask(mQuestDao).execute(quest);
    }
    private static class deleteAsyncTask extends AsyncTask<Quest, Void, Void>
    {
        private QuestDao mAsyncTaskDao;

        deleteAsyncTask(QuestDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Quest... params)
        {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void deleteAll ()
    {
        new QuestRepository.deleteAllAsyncTask(mQuestDao).execute();
    }
    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private QuestDao mAsyncTaskDao;

        deleteAllAsyncTask(QuestDao dao)
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
}
