package com.farazannajmi.majesticlife;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.backtory.java.internal.BacktoryUser;
import com.farazannajmi.majesticlife.DataStructures.Avatar;
import com.farazannajmi.majesticlife.DataStructures.AvatarItem;
import com.farazannajmi.majesticlife.DataStructures.AvatarItemViewModel;
import com.farazannajmi.majesticlife.DataStructures.AvatarViewModel;
import com.farazannajmi.majesticlife.DataStructures.Faaliat;
import com.farazannajmi.majesticlife.DataStructures.FaaliatRepetitions;
import com.farazannajmi.majesticlife.DataStructures.FaaliatRepetitionsViewModel;
import com.farazannajmi.majesticlife.DataStructures.FaaliatSkill;
import com.farazannajmi.majesticlife.DataStructures.FaaliatSkillViewModel;
import com.farazannajmi.majesticlife.DataStructures.FaaliatViewModel;
import com.farazannajmi.majesticlife.DataStructures.MajesticLifeRoomDatabase;
import com.farazannajmi.majesticlife.DataStructures.PlanCell;
import com.farazannajmi.majesticlife.DataStructures.PlanCellViewModel;
import com.farazannajmi.majesticlife.DataStructures.Quest;
import com.farazannajmi.majesticlife.DataStructures.QuestSkill;
import com.farazannajmi.majesticlife.DataStructures.QuestViewModel;
import com.farazannajmi.majesticlife.DataStructures.Skill;
import com.farazannajmi.majesticlife.DataStructures.SkillViewModel;
import com.farazannajmi.majesticlife.DataStructures.User;
import com.farazannajmi.majesticlife.DataStructures.UserViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Narges on 5/30/2018.
 * <div>Icons made by <a href="https://www.flaticon.com/authors/freepik" title="leaf">leaf</a> from <a href="https://www.flaticon.com/"     title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/"     title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
 * https://www.flaticon.com/packs/user-interface-30/2
 */

public class DataHolder
{
    public static int LevelUpReward = 50;

    public static User ThisUser;
    public static BacktoryUser CurrentBacktoryUser;
    public static ArrayList<Faaliat> Faaliats;
    public static ArrayList<Skill> Skills;
    public static ArrayList<PlanCell> PlanCells;
    public static ArrayList<Quest> Quests;
    public static int FaaliatRepetitionsIdCounter;
    public static Avatar UserAvatar;
    public static ArrayList<AvatarItem> AvatarItems;

    public static ArrayList<Integer> FaaliatAvatars;
    public static ArrayList<Integer> SkillAvatars;

    public static boolean IsDatabaseCreated = false;

    public static void InitialDataStructures()
    {
        IsDatabaseCreated = false;

        FaaliatAvatars = new ArrayList<Integer>();
        FaaliatAvatars.add(R.drawable.ic_majestic_activities);
        FaaliatAvatars.add(R.drawable.ic_bag);
        FaaliatAvatars.add(R.drawable.ic_book);
        FaaliatAvatars.add(R.drawable.ic_camera);
        FaaliatAvatars.add(R.drawable.ic_computer);
        FaaliatAvatars.add(R.drawable.ic_conversation);
        FaaliatAvatars.add(R.drawable.ic_diamond);
        FaaliatAvatars.add(R.drawable.ic_edit);
        FaaliatAvatars.add(R.drawable.ic_eye);
        FaaliatAvatars.add(R.drawable.ic_flask);
        FaaliatAvatars.add(R.drawable.ic_gamepad);
        FaaliatAvatars.add(R.drawable.ic_idea);
        FaaliatAvatars.add(R.drawable.ic_leaf);
        FaaliatAvatars.add(R.drawable.ic_list);
        FaaliatAvatars.add(R.drawable.ic_music);
        FaaliatAvatars.add(R.drawable.ic_news);
        FaaliatAvatars.add(R.drawable.ic_paint);
        FaaliatAvatars.add(R.drawable.ic_real_state);
        FaaliatAvatars.add(R.drawable.ic_rocket);
        FaaliatAvatars.add(R.drawable.ic_search);
        FaaliatAvatars.add(R.drawable.ic_speed);
        FaaliatAvatars.add(R.drawable.ic_target);

        SkillAvatars = new ArrayList<Integer>();
        SkillAvatars.add(R.drawable.ic_skills);
        SkillAvatars.add(R.drawable.ic_bag);
        SkillAvatars.add(R.drawable.ic_book);
        SkillAvatars.add(R.drawable.ic_camera);
        SkillAvatars.add(R.drawable.ic_computer);
        SkillAvatars.add(R.drawable.ic_conversation);
        SkillAvatars.add(R.drawable.ic_diamond);
        SkillAvatars.add(R.drawable.ic_edit);
        SkillAvatars.add(R.drawable.ic_eye);
        SkillAvatars.add(R.drawable.ic_flask);
        SkillAvatars.add(R.drawable.ic_gamepad);
        SkillAvatars.add(R.drawable.ic_idea);
        SkillAvatars.add(R.drawable.ic_leaf);
        SkillAvatars.add(R.drawable.ic_list);
        SkillAvatars.add(R.drawable.ic_music);
        SkillAvatars.add(R.drawable.ic_news);
        SkillAvatars.add(R.drawable.ic_paint);
        SkillAvatars.add(R.drawable.ic_real_state);
        SkillAvatars.add(R.drawable.ic_rocket);
        SkillAvatars.add(R.drawable.ic_search);
        SkillAvatars.add(R.drawable.ic_speed);
        SkillAvatars.add(R.drawable.ic_target);
    }

    public static void LoadUser(FragmentActivity activity, LifecycleOwner owner)
    {
        Log.d("Data", "Loading User from Database");

        //getting user:
        UserViewModel userViewModel = ViewModelProviders.of(activity).get(UserViewModel.class);
        userViewModel.getUser().observe(owner, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                DataHolder.ThisUser = user;
            }
        });
    }

    public static void LoadData(FragmentActivity activity, LifecycleOwner owner)
    {
        Log.d("Data", "Loading data from Database");

        //getting user:
        UserViewModel userViewModel = ViewModelProviders.of(activity).get(UserViewModel.class);
        userViewModel.getUser().observe(owner, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                DataHolder.ThisUser = user;
            }
        });

        //getting Faaliats:
        FaaliatViewModel faaliatViewModel = ViewModelProviders.of(activity).get(FaaliatViewModel.class);
        faaliatViewModel.getAllFaaliats().observe(owner, new Observer<List<Faaliat>>() {
            @Override
            public void onChanged(@Nullable List<Faaliat> faaliats) {
                DataHolder.Faaliats = (ArrayList) faaliats;
            }
        });

        //getting Skills:
        SkillViewModel skillViewModel = ViewModelProviders.of(activity).get(SkillViewModel.class);
        skillViewModel.getAllSkills().observe(owner, new Observer<List<Skill>>() {
            @Override
            public void onChanged(@Nullable List<Skill> skills) {
                DataHolder.Skills = (ArrayList) skills;
            }
        });

        //getting PlanCells:
        PlanCellViewModel planCellViewModel = ViewModelProviders.of(activity).get(PlanCellViewModel.class);
        planCellViewModel.getAllPlanCells().observe(owner, new Observer<List<PlanCell>>() {
            @Override
            public void onChanged(@Nullable List<PlanCell> planCells) {
                DataHolder.PlanCells = (ArrayList) planCells;
            }
        });

        //getting Quests:
        QuestViewModel questViewModel = ViewModelProviders.of(activity).get(QuestViewModel.class);
        questViewModel.getAllQuests().observe(owner, new Observer<List<Quest>>() {
            @Override
            public void onChanged(@Nullable List<Quest> quests) {
                DataHolder.Quests = (ArrayList) quests;
            }
        });

        //getting FaaliatRepetitions
        FaaliatRepetitionsViewModel faaliatRepetitionsViewModel = ViewModelProviders.of(activity).get(FaaliatRepetitionsViewModel.class);
        faaliatRepetitionsViewModel.getAllFaaliatRepetitions().observe(owner, new Observer<List<FaaliatRepetitions>>() {
            @Override
            public void onChanged(@Nullable List<FaaliatRepetitions> faaliatRepetitions) {
                FaaliatRepetitionsIdCounter = faaliatRepetitions.size();
            }
        });

        //getting AvatarItems
        AvatarItemViewModel avatarItemViewModel = ViewModelProviders.of(activity).get(AvatarItemViewModel.class);
        avatarItemViewModel.getAvatarItems().observe(owner, new Observer<List<AvatarItem>>() {
            @Override
            public void onChanged(@Nullable List<AvatarItem> avatarItems) {
                AvatarItems = (ArrayList) avatarItems;
            }
        });

        //getting UserAvatar
        AvatarViewModel avatarViewModel = ViewModelProviders.of(activity).get(AvatarViewModel.class);
        avatarViewModel.getAvatar().observe(owner, new Observer<Avatar>() {
            @Override
            public void onChanged(@Nullable Avatar avatar) {
                UserAvatar = avatar;
            }
        });
    }

    public static void LoadFaaliatSkills(FragmentActivity activity, LifecycleOwner owner)
    {
        FaaliatSkillViewModel faaliatSkillViewModel = ViewModelProviders.of(activity).get(FaaliatSkillViewModel.class);

        for(int faaliatCounter = 0; faaliatCounter < Faaliats.size(); faaliatCounter++)
        {
            faaliatSkillViewModel.getSkillsForFaaliat(Faaliats.get(faaliatCounter)).observe(owner, new Observer<List<FaaliatSkill>>() {
                @Override
                public void onChanged(@Nullable List<FaaliatSkill> faaliatSkills)
                {
                    if(faaliatSkills.size() != 0)
                    {
                        for (int i = 0; i < Faaliats.size(); i++)
                        {
                            if (Faaliats.get(i).getFaaliat_ID() == faaliatSkills.get(0).getFaaliat_ID())
                                Faaliats.get(i).FaaliatSkills = (ArrayList) faaliatSkills;
                        }
                    }
                }
            });
        }
    }
}
