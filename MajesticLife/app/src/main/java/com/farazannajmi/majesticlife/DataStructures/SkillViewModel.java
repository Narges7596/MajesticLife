package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class SkillViewModel extends AndroidViewModel
{
    private SkillRepository mRepository;
    private LiveData<List<Skill>> mAllSkills;

    public LiveData<List<Skill>> getAllSkills() {return mAllSkills;}

    public SkillViewModel (Application application)
    {
        super(application);
        mRepository = new SkillRepository(application);
        mAllSkills = mRepository.getAllSkills();
    }

    public void insert(Skill skill)
    {
        mRepository.insert(skill);
    }

    public void update(Skill skill)
    {
        mRepository.update(skill);
    }

    public void delete(Skill skill)
    {
        mRepository.delete(skill);
    }
}