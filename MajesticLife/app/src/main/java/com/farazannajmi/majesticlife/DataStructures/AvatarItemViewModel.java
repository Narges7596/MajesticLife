package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Narges on 6/25/2018.
 */

public class AvatarItemViewModel extends AndroidViewModel
{
    private AvatarItemRepository mRepository;
    private LiveData<List<AvatarItem>> mAvatarItem;

    public LiveData<List<AvatarItem>> getAvatarItems() {return mAvatarItem;}

    public AvatarItemViewModel (Application application)
    {
        super(application);
        mRepository = new AvatarItemRepository(application);
        mAvatarItem = mRepository.getAvatarItems();
    }

    public void insert(AvatarItem avatarItem) {mRepository.insert(avatarItem);}

    public void update(AvatarItem avatarItem) {mRepository.update(avatarItem);}

    public void delete(AvatarItem avatarItem) { mRepository.delete(avatarItem);}
}
