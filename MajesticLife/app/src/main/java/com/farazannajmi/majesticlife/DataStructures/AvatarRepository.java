package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

/**
 * Created by Narges on 6/25/2018.
 */

public class AvatarRepository
{
    private AvatarDao mAvatarDao;
    private LiveData<Avatar> mAvatar;

    LiveData<Avatar> getAvatar()
    {
        return mAvatar;
    }

    public AvatarRepository(Application application)
    {
        MajesticLifeRoomDatabase db = MajesticLifeRoomDatabase.getDatabase(application);
        mAvatarDao = db.avatarDao();
        mAvatar = mAvatarDao.getAvatar();
    }

    public void insert (Avatar avatar)
    {
        new AvatarRepository.insertAsyncTask(mAvatarDao).execute(avatar);
    }

    public void update (Avatar avatar)
    {
        new AvatarRepository.updateAsyncTask(mAvatarDao).execute(avatar);
    }

    public void delete (Avatar avatar)
    {
        new AvatarRepository.deleteAsyncTask(mAvatarDao).execute(avatar);
    }

    private static class insertAsyncTask extends AsyncTask<Avatar, Void, Void>
    {
        private AvatarDao mAsyncTaskDao;

        insertAsyncTask(AvatarDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Avatar... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Avatar, Void, Void>
    {
        private AvatarDao mAsyncTaskDao;

        updateAsyncTask(AvatarDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Avatar... params)
        {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Avatar, Void, Void>
    {
        private AvatarDao mAsyncTaskDao;

        deleteAsyncTask(AvatarDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Avatar... params)
        {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
