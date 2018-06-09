package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class FaaliatRepository
{
    private FaaliatDao mFaaliatDao;
    private LiveData<List<Faaliat>> mAllFaaliats;

    public FaaliatRepository(Application application)
    {
        MajesticLifeRoomDatabase db = MajesticLifeRoomDatabase.getDatabase(application);
        mFaaliatDao = db.faaliatDao();
        mAllFaaliats = mFaaliatDao.getAllFaaliats();
    }

    //Room executes all queries on a separate thread.
    //Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Faaliat>> getAllFaaliats()
    {
        return mAllFaaliats;
    }

    /*a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
     Room ensures that you don't do any long-running operations on the main thread, blocking the UI.*/
    public void insert (Faaliat faaliat)
    {
        new FaaliatRepository.insertAsyncTask(mFaaliatDao).execute(faaliat);
    }

    public void update (Faaliat faaliat)
    {
        new FaaliatRepository.updateAsyncTask(mFaaliatDao).execute(faaliat);
    }

    public void delete (Faaliat faaliat)
    {
        new FaaliatRepository.deleteAsyncTask(mFaaliatDao).execute(faaliat);
    }

    private static class insertAsyncTask extends AsyncTask<Faaliat, Void, Void>
    {
        private FaaliatDao mAsyncTaskDao;

        insertAsyncTask(FaaliatDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Faaliat... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Faaliat, Void, Void>
    {
        private FaaliatDao mAsyncTaskDao;

        updateAsyncTask(FaaliatDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Faaliat... params)
        {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Faaliat, Void, Void>
    {
        private FaaliatDao mAsyncTaskDao;

        deleteAsyncTask(FaaliatDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Faaliat... params)
        {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
