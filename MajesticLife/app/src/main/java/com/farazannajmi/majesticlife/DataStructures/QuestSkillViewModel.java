package com.farazannajmi.majesticlife.DataStructures;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Narges on 6/9/2018.
 */

public class QuestSkillViewModel extends AndroidViewModel
{
    private QuestSkillRepository mRepository;
    private LiveData<List<QuestSkill>> mAllQuestSkills;

    public LiveData<List<QuestSkill>> getAllQuestSkills() {return mAllQuestSkills;}

    public QuestSkillViewModel (Application application)
    {
        super(application);
        mRepository = new QuestSkillRepository(application);
        mAllQuestSkills = mRepository.getAllQuestSkills();
    }

    public void insert(QuestSkill questSkill)
    {
        mRepository.insert(questSkill);
    }

    public void update(QuestSkill questSkill)
    {
        mRepository.update(questSkill);
    }

    public void delete(QuestSkill questSkill)
    {
        mRepository.delete(questSkill);
    }

    public void deleteAll()
    {
        mRepository.deleteAll();
    }

    public void getSkillsForQuest(Quest quest)
    {
        mRepository.getSkillsForQuest(quest);
    }
}