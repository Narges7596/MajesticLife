package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class UserRepository
{
    private UserDao mUserDao;
    private LiveData<User> mUser;

    LiveData<User> getUser()
    {
        return mUser;
    }

    public UserRepository(Application application)
    {
        MajesticLifeRoomDatabase db = MajesticLifeRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mUser = mUserDao.getUser();
    }

    public void insert (User user)
    {
        new UserRepository.insertAsyncTask(mUserDao).execute(user);
    }

    public void update (User user)
    {
        new UserRepository.updateAsyncTask(mUserDao).execute(user);
    }

    public void delete (User user)
    {
        //new UserRepository.updateAsyncTask(mUserDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void>
    {
        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<User, Void, Void>
    {
        private UserDao mAsyncTaskDao;

        updateAsyncTask(UserDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params)
        {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<User, Void, Void>
    {
        private UserDao mAsyncTaskDao;

        deleteAsyncTask(UserDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params)
        {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
