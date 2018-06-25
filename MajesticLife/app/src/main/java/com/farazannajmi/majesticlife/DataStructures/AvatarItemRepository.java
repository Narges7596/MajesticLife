package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Narges on 6/25/2018.
 */

public class AvatarItemRepository
{
    private AvatarItemDao mAvatarItemDao;
    private LiveData<List<AvatarItem>> mAllAvatarItems;

    LiveData<List<AvatarItem>> getAvatarItems()
    {
        return mAllAvatarItems;
    }

    public AvatarItemRepository(Application application)
    {
        MajesticLifeRoomDatabase db = MajesticLifeRoomDatabase.getDatabase(application);
        mAvatarItemDao = db.avatarItemDao();
        mAllAvatarItems = mAvatarItemDao.getAllAvatarItem();
    }

    public void insert (AvatarItem avatarItem)
    {
        new AvatarItemRepository.insertAsyncTask(mAvatarItemDao).execute(avatarItem);
    }

    public void update (AvatarItem avatarItem)
    {
        new AvatarItemRepository.updateAsyncTask(mAvatarItemDao).execute(avatarItem);
    }

    public void delete (AvatarItem avatarItem)
    {
        new AvatarItemRepository.deleteAsyncTask(mAvatarItemDao).execute(avatarItem);
    }

    private static class insertAsyncTask extends AsyncTask<AvatarItem, Void, Void>
    {
        private AvatarItemDao mAsyncTaskDao;

        insertAsyncTask(AvatarItemDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AvatarItem... params)
        {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<AvatarItem, Void, Void>
    {
        private AvatarItemDao mAsyncTaskDao;

        updateAsyncTask(AvatarItemDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AvatarItem... params)
        {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<AvatarItem, Void, Void>
    {
        private AvatarItemDao mAsyncTaskDao;

        deleteAsyncTask(AvatarItemDao dao)
        {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final AvatarItem... params)
        {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
