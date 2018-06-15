package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class FaaliatSkillRepository
{
    private FaaliatSkillDao mFaaliatSkillDao;
    private LiveData<List<FaaliatSkill>> mAllFaaliatSkills;

    public FaaliatSkillRepository(Application application)
    {
        MajesticLifeRoomDatabase db = MajesticLifeRoomDatabase.getDatabase(application);
        mFaaliatSkillDao = db.faaliatSkillDao();
        mAllFaaliatSkills = mFaaliatSkillDao.getAllFaaliatSkills();
    }

    //Room executes all queries on a separate thread.
    //Observed LiveData will notify the observer when the data has changed.
    LiveData<List<FaaliatSkill>> getAllFaaliatSkills()
    {
        return mAllFaaliatSkills;
    }

    /*a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
     Room ensures that you don't do any long-running operations on the main thread, blocking the UI.*/
    public void insert (FaaliatSkill faaliatSkill)
    {
        new FaaliatSkillRepository.insertAsyncTask(mFaaliatSkillDao).execute(faaliatSkill);
    }
    private static class insertAsyncTask extends AsyncTask<FaaliatSkill, Void, Void>
    {
        private FaaliatSkillDao mAsyncTaskDao;

        insertAsyncTask(FaaliatSkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FaaliatSkill... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update (FaaliatSkill faaliatSkill)
    {
        new FaaliatSkillRepository.updateAsyncTask(mFaaliatSkillDao).execute(faaliatSkill);
    }
    private static class updateAsyncTask extends AsyncTask<FaaliatSkill, Void, Void>
    {
        private FaaliatSkillDao mAsyncTaskDao;

        updateAsyncTask(FaaliatSkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FaaliatSkill... params)
        {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    public void delete (FaaliatSkill faaliatSkill)
    {
        new FaaliatSkillRepository.deleteAsyncTask(mFaaliatSkillDao).execute(faaliatSkill);
    }
    private static class deleteAsyncTask extends AsyncTask<FaaliatSkill, Void, Void>
    {
        private FaaliatSkillDao mAsyncTaskDao;

        deleteAsyncTask(FaaliatSkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FaaliatSkill... params)
        {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void deleteAll ()
    {
        new FaaliatSkillRepository.deleteAllAsyncTask(mFaaliatSkillDao).execute();
    }
    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private FaaliatSkillDao mAsyncTaskDao;

        deleteAllAsyncTask(FaaliatSkillDao dao)
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

    public LiveData<List<FaaliatSkill>> getSkillsForFaaliat (Faaliat faaliat)
    {
        return mFaaliatSkillDao.getSkillsForFaaliat(faaliat.getFaaliat_ID());
    }
}
