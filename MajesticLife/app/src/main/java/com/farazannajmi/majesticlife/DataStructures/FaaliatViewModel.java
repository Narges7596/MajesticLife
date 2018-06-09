package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class FaaliatViewModel extends AndroidViewModel
{
    private FaaliatRepository mRepository;
    private LiveData<List<Faaliat>> mAllFaaliats;

    public LiveData<List<Faaliat>> getAllFaaliats() {return mAllFaaliats;}

    public FaaliatViewModel (Application application)
    {
        super(application);
        mRepository = new FaaliatRepository(application);
        mAllFaaliats = mRepository.getAllFaaliats();
    }

    public void insert(Faaliat faaliat)
    {
        mRepository.insert(faaliat);
    }

    public void update(Faaliat faaliat)
    {
        mRepository.update(faaliat);
    }

    public void delete(Faaliat faaliat)
    {
        mRepository.delete(faaliat);
    }
}