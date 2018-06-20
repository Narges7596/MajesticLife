package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Narges on 6/20/2018.
 */

public class FaaliatRepetitionsViewModel extends AndroidViewModel
{
    private FaaliatRepetitionsRepository mRepository;
    private LiveData<List<FaaliatRepetitions>> mAllFaaliatRepetitions;

    public LiveData<List<FaaliatRepetitions>> getAllFaaliatRepetitions() {return mAllFaaliatRepetitions;}

    public FaaliatRepetitionsViewModel (Application application)
    {
        super(application);
        mRepository = new FaaliatRepetitionsRepository(application);
        mAllFaaliatRepetitions = mRepository.getAllFaaliatRepetitions();
    }

    public void insert(FaaliatRepetitions faaliatRepetitions)
    {
        mRepository.insert(faaliatRepetitions);
    }

    public void update(FaaliatRepetitions faaliatRepetitions)
    {
        mRepository.update(faaliatRepetitions);
    }

    public void delete(FaaliatRepetitions faaliatRepetitions)
    {
        mRepository.delete(faaliatRepetitions);
    }

    public void deleteAll()
    {
        mRepository.deleteAll();
    }

    public LiveData<List<FaaliatRepetitions>> getAllFaaliatRepsForFaaliat(int faaliat_ID)
    {
        return mRepository.getAllFaaliatRepsForFaaliat(faaliat_ID);
    }

    public LiveData<List<FaaliatRepetitions>> getFaaliatRepsForFaaliatDate(int faaliat_ID, String date)
    {
        return mRepository.getFaaliatRepsForFaaliatDate(faaliat_ID, date);
    }
}