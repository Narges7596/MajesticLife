package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Narges on 6/7/2018.
 */

public class PlanCellRepository
{
    private PlanCellDao mPlanCellDao;
    private LiveData<List<PlanCell>> mAllPlanCells;

    PlanCellRepository(Application application)
    {
        MajesticLifeRoomDatabase db = MajesticLifeRoomDatabase.getDatabase(application);
        mPlanCellDao = db.planCellDao();
        mAllPlanCells = mPlanCellDao.getAllPlanCells();
    }

    //Room executes all queries on a separate thread.
    //Observed LiveData will notify the observer when the data has changed.
    LiveData<List<PlanCell>> getAllPlanCells()
    {
        return mAllPlanCells;
    }

    /*a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
     Room ensures that you don't do any long-running operations on the main thread, blocking the UI.*/
    public void insert (PlanCell planCell)
    {
        new insertAsyncTask(mPlanCellDao).execute(planCell);
    }

    private static class insertAsyncTask extends AsyncTask<PlanCell, Void, Void>
    {
        private PlanCellDao mAsyncTaskDao;

        insertAsyncTask(PlanCellDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PlanCell... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
