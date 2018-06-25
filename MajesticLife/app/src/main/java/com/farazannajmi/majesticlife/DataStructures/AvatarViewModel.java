package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

/**
 * Created by Narges on 6/25/2018.
 */

public class AvatarViewModel extends AndroidViewModel
{
    private AvatarRepository mRepository;
    private LiveData<Avatar> mAvatar;

    public LiveData<Avatar> getAvatar() {return mAvatar;}

    public AvatarViewModel (Application application)
    {
        super(application);
        mRepository = new AvatarRepository(application);
        mAvatar = mRepository.getAvatar();
    }

    public void insert(Avatar avatar)
    {
        mRepository.insert(avatar);
    }

    public void update(Avatar avatar)
    {
        mRepository.update(avatar);
    }

    public void delete(Avatar avatar)
    {
        mRepository.delete(avatar);
    }
}