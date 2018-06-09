package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Narges on 6/7/2018.
 */

public class PlanCellViewModel extends AndroidViewModel
{
    private PlanCellRepository mRepository;
    private LiveData<List<PlanCell>> mAllPlanCells;

    public LiveData<List<PlanCell>> getAllPlanCells() {return mAllPlanCells;}

    public PlanCellViewModel (Application application)
    {
        super(application);
        mRepository = new PlanCellRepository(application);
        mAllPlanCells = mRepository.getAllPlanCells();
    }

    public void insert(PlanCell planCell)
    {
        mRepository.insert(planCell);
    }
}