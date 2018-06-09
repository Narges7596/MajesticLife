package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class QuestViewModel extends AndroidViewModel
{
    private QuestRepository mRepository;
    private LiveData<List<Quest>> mAllQuests;

    public LiveData<List<Quest>> getAllQuests() {return mAllQuests;}

    public QuestViewModel (Application application)
    {
        super(application);
        mRepository = new QuestRepository(application);
        mAllQuests = mRepository.getAllQuests();
    }

    public void insert(Quest quest)
    {
        mRepository.insert(quest);
    }

    public void update(Quest quest)
    {
        mRepository.update(quest);
    }

    public void delete(Quest quest)
    {
        mRepository.delete(quest);
    }

    public void deleteAll()
    {
        mRepository.deleteAll();
    }
}