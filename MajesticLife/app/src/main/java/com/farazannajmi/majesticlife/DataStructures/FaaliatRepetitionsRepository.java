package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Narges on 6/20/2018.
 */

public class FaaliatRepetitionsRepository
{
    private FaaliatRepetitionsDao mFaaliatRepetitionsDao;
    private LiveData<List<FaaliatRepetitions>> mAllFaaliatRepetitions;

    public FaaliatRepetitionsRepository(Application application)
    {
        MajesticLifeRoomDatabase db = MajesticLifeRoomDatabase.getDatabase(application);
        mFaaliatRepetitionsDao = db.faaliatRepetitionsDao();
        mAllFaaliatRepetitions = mFaaliatRepetitionsDao.getAllFaaliatRepetitions();
    }

    //Room executes all queries on a separate thread.
    //Observed LiveData will notify the observer when the data has changed.
    LiveData<List<FaaliatRepetitions>> getAllFaaliatRepetitions()
    {
        return mAllFaaliatRepetitions;
    }

    /*a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
     Room ensures that you don't do any long-running operations on the main thread, blocking the UI.*/
    public void insert (FaaliatRepetitions faaliatRepetitions)
    {
        new FaaliatRepetitionsRepository.insertAsyncTask(mFaaliatRepetitionsDao).execute(faaliatRepetitions);
    }

    private static class insertAsyncTask extends AsyncTask<FaaliatRepetitions, Void, Void>
    {
        private FaaliatRepetitionsDao mAsyncTaskDao;

        insertAsyncTask(FaaliatRepetitionsDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FaaliatRepetitions... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update (FaaliatRepetitions faaliatRepetitions)
    {
        new FaaliatRepetitionsRepository.updateAsyncTask(mFaaliatRepetitionsDao).execute(faaliatRepetitions);
    }
    private static class updateAsyncTask extends AsyncTask<FaaliatRepetitions, Void, Void>
    {
        private FaaliatRepetitionsDao mAsyncTaskDao;

        updateAsyncTask(FaaliatRepetitionsDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FaaliatRepetitions... params)
        {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    public void delete (FaaliatRepetitions faaliatRepetitions)
    {
        new FaaliatRepetitionsRepository.deleteAsyncTask(mFaaliatRepetitionsDao).execute(faaliatRepetitions);
    }
    private static class deleteAsyncTask extends AsyncTask<FaaliatRepetitions, Void, Void>
    {
        private FaaliatRepetitionsDao mAsyncTaskDao;

        deleteAsyncTask(FaaliatRepetitionsDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FaaliatRepetitions... params)
        {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public void deleteAll ()
    {
        new FaaliatRepetitionsRepository.deleteAllAsyncTask(mFaaliatRepetitionsDao).execute();
    }
    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private FaaliatRepetitionsDao mAsyncTaskDao;

        deleteAllAsyncTask(FaaliatRepetitionsDao dao)
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

    public LiveData<List<FaaliatRepetitions>> getAllFaaliatRepsForFaaliat (int faaliat_ID)
    {
        return mFaaliatRepetitionsDao.getAllFaaliatRepsForFaaliat(faaliat_ID);
    }

    public LiveData<List<FaaliatRepetitions>> getFaaliatRepsForFaaliatDate (int faaliat_ID, String date)
    {
        return mFaaliatRepetitionsDao.getFaaliatRepsForFaaliatDate(faaliat_ID, date);
    }
}
