package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class SkillRepository
{
    private SkillDao mSkillDao;
    private LiveData<List<Skill>> mAllSkills;

    public SkillRepository(Application application)
    {
        MajesticLifeRoomDatabase db = MajesticLifeRoomDatabase.getDatabase(application);
        mSkillDao = db.skillDao();
        mAllSkills = mSkillDao.getAllSkills();
    }

    //Room executes all queries on a separate thread.
    //Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Skill>> getAllSkills()
    {
        return mAllSkills;
    }

    /*a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
     Room ensures that you don't do any long-running operations on the main thread, blocking the UI.*/
    public void insert (Skill skill)
    {
        new SkillRepository.insertAsyncTask(mSkillDao).execute(skill);
    }

    public void update (Skill skill)
    {
        new SkillRepository.updateAsyncTask(mSkillDao).execute(skill);
    }

    public void delete (Skill skill)
    {
        new SkillRepository.deleteAsyncTask(mSkillDao).execute(skill);
    }

    private static class insertAsyncTask extends AsyncTask<Skill, Void, Void>
    {
        private SkillDao mAsyncTaskDao;

        insertAsyncTask(SkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Skill... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Skill, Void, Void>
    {
        private SkillDao mAsyncTaskDao;

        updateAsyncTask(SkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Skill... params)
        {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Skill, Void, Void>
    {
        private SkillDao mAsyncTaskDao;

        deleteAsyncTask(SkillDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Skill... params)
        {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
