package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class FaaliatSkillViewModel extends AndroidViewModel
{
    private FaaliatSkillRepository mRepository;
    private LiveData<List<FaaliatSkill>> mAllFaaliatSkills;

    public LiveData<List<FaaliatSkill>> getAllFaaliatSkills() {return mAllFaaliatSkills;}

    public FaaliatSkillViewModel (Application application)
    {
        super(application);
        mRepository = new FaaliatSkillRepository(application);
        mAllFaaliatSkills = mRepository.getAllFaaliatSkills();
    }

    public void insert(FaaliatSkill faaliatSkill)
    {
        mRepository.insert(faaliatSkill);
    }

    public void update(FaaliatSkill faaliatSkill)
    {
        mRepository.update(faaliatSkill);
    }

    public void delete(FaaliatSkill faaliatSkill)
    {
        mRepository.delete(faaliatSkill);
    }

    public void deleteAll()
    {
        mRepository.deleteAll();
    }

    public void getSkillsForFaaliat(Faaliat faaliat)
    {
        mRepository.getSkillsForFaaliat(faaliat);
    }
}